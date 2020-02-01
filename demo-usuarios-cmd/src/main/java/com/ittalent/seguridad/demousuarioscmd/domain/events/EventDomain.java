package com.ittalent.seguridad.demousuarioscmd.domain.events;

public interface EventDomain {

    String getEntityId();
    String getMetadata();
    String getEntityType();

}
