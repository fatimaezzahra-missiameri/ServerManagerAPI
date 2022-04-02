package com.femissiameri.api.service;

import com.femissiameri.api.model.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * The interface Server service.
 */
public interface ServerService {

    /**
     * Add a new server.
     *
     * @param server the server to add.
     * @return the added server.
     */
    Server create(Server server) throws FileNotFoundException;

    /**
     * Ping a server.
     *
     * @param ipAddress the ip address of the server.
     * @return the pinged server.
     */
    Server ping(String ipAddress) throws IOException;

    /**
     * List the number of servers defined by the limit param.
     *
     * @param limit limits the number of retrieved servers.
     * @return the collection of servers.
     */
    Collection<Server> list(int limit);

    /**
     * Get a server by its ID.
     *
     * @param id the id.
     * @return the server.
     */
    Server get(Long id);

    /**
     * Update a server.
     *
     * @param server the server to update.
     * @return the updated server.
     */
    Server update(Server server);

    /**
     * Delete a server by its ID.
     *
     * @param id the id
     * @return boolean indicates if the server deleted or not.
     */
    Boolean delete(Long id);

}
