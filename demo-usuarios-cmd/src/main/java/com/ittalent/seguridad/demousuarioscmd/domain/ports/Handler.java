package com.ittalent.seguridad.demousuarioscmd.domain.ports;

import com.ittalent.seguridad.demousuarioscmd.domain.model.User;

public interface Handler<Command> {
public User handle(Command cmd) throws Exception;
}
