package com.ittalent.seguridad.demousuarioscmd.controllers;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ittalent.seguridad.demousuarioscmd.application.adapters.CreateUserCommandImpl;
import com.ittalent.seguridad.demousuarioscmd.application.adapters.UserServiceApplicationImpl;
import com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.constants.RestConstants;
import com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.domain.ApiResponse;
import com.ittalent.seguridad.demousuarioscmd.infra.adapters.http.rest.domain.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j

public class UsuariosController {

	@Autowired
	private HttpServletRequest context;
	@Autowired
	UserServiceApplicationImpl serviceApplicationImpl;

	final static Logger log = Logger.getLogger(UsuariosController.class);

	
	@RequestMapping(path = "/security/v1.0/users", method = POST)
	public ResponseEntity<ApiResponse> createProduct(@RequestBody @Valid User request) {

		log.info(context.getHeader("Authorization"));

		log.info("Create User request." + request.getMetadata());

		CreateUserCommandImpl cmd = new CreateUserCommandImpl(request);

		try {

			cmd.setToken(this.createJWT("1", "issuerTest", "subject", 3000));
			if (serviceApplicationImpl.addUser(cmd))
				
				log.info("User Created successful");
			else {
				log.info("User not Created ");
				return new ResponseEntity<ApiResponse>(this.buildErrorRes("User not Created"), HttpStatus.BAD_REQUEST);
			}

		} catch (DataIntegrityViolationException e) {
			log.error("User Created Exception" + request.getMetadata());
			return new ResponseEntity<ApiResponse>(this.buildErrorRes("Email already registered"),
					HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			log.error("User Created Exception" + request.getMetadata());
			return new ResponseEntity<ApiResponse>(this.buildErrorRes(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ApiResponse>(this.buildSuccessRes("User Created",cmd.getIdUsuario(),cmd.getToken()), HttpStatus.OK);
	}



	private ApiResponse buildSuccessRes(String msg,String idIsuario,String token) {
		ApiResponse res = new ApiResponse();
		res.setCode(RestConstants.SUCCESS_CODE);
		res.setType(RestConstants.SUCCESS_RESPONSE);
		res.getMessage().add(msg);
		res.setActive(true);
		res.setCreated(new Date());
		res.setId(idIsuario);
		res.setLastLogin(new Date());
		res.setModified(new Date());
		res.setToken(token);
		return res;
	}

	/**
	 * API Error response
	 *
	 * @return
	 */
	private ApiResponse buildErrorRes(String error) {
		ApiResponse res = new ApiResponse();
		res.setCode(RestConstants.ERROR_CODE);
		res.setType(RestConstants.SYSTEM_ERROR);
		res.getMessage().add(error);
		return res;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	public String createJWT(String id, String issuer, String subject, long ttlMillis) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRET_KEY");
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

}
