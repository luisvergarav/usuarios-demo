package com.ittalent.seguridad.demousuarioscmd.domain.ports;

import com.ittalent.seguridad.demousuarioscmd.domain.model.User;

public interface CommandBus<Command> {

    public User execute(Command command) throws Exception;
}