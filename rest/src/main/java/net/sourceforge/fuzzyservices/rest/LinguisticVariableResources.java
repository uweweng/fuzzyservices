/*
 *
 *  Copyright (C) 2007  Uwe Weng
 *
 *  This file is part of Fuzzy Services, a library for processing fuzzy
 *  information.
 *
 *  Fuzzy Services are free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  Fuzzy Services are distributed in the hope that they will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Fuzzy Services; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.rest;

import java.net.URI;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import net.sourceforge.fuzzyservices.beans.LinguisticVariable;
import net.sourceforge.fuzzyservices.beans.dao.DaoI;
import net.sourceforge.fuzzyservices.beans.dao.LinguisticVariableDao;

/**
 * This class represents linguistic variable beans as resources hosted at the URI path 
 * "/linguistic_variables". For instance,
 * 
 * http://localhost:8080/net/sourceforge/fuzzyservices/linguistic_variables/4711
 * curl -i http://localhost:8080/net/sourceforge/fuzzyservices/linguistic_variables/4711 -H ’Accept: text/plain’
 *
 * @see LinguisticVariableResource
 * 
 * @version 1.0
 * @author Uwe Weng
 */
@Path("/linguistic_variables")
public class LinguisticVariableResources {

    /**
     * Provides both static and dynamic, per-request information, 
     * about the components of a request URI.
     */
    @Context
    UriInfo uriInfo;
    /**
     * The data access object (as data store)
     */
    private DaoI<LinguisticVariable, Integer> dao = new LinguisticVariableDao();
    /**
     * Returns the resource identified by <code>id</code>.
     *
     * @param id the identifier of the bean
     * @return the status of the operation containing the resource with the bean
     */
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        LinguisticVariable bean = dao.findById(id);
        if (bean == null) {
            return Response.status(404).build();
        }
        return Response.ok(new LinguisticVariableResource(bean)).build();
    }

    /**
     * Creates a new resource.
     *
     * @param newResource the new resource to be created
     * @return the result of the operation as response object with the new
     * resource as entity
     */
    @POST
    public Response create(LinguisticVariableResource newResource) {
        if ((newResource == null) || (newResource.getBean() == null)) {
            return Response.status(404).build();
        }
        dao.create(newResource.getBean());
        URI myResourceUri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newResource.getBean().getId())).build();
        return Response.created(myResourceUri).entity(newResource).build();
    }

    /**
     * Updates an existing resource. Note that the ID in the bean passed as args
     * must be the same as that which was passed as args in the URL.
     *
     * @param resource the resource to be updated
     * @return the status of the operation.
     */
    @PUT
    public Response put(LinguisticVariableResource resource) {
        LinguisticVariableResource response = new LinguisticVariableResource(dao.update(resource.getBean()));
        return Response.ok(response).build();
    }

    /**
     * Removes the bean from the data store.
     *
     * @param id the identifier of the bean
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        dao.removeById(id);
    }

    /**
     * Removes the resource from the data store.
     *
     * @param resource the resource to be deleted
     */
    @DELETE
    public void delete(LinguisticVariableResource resource) {
        dao.removeById(resource.getBean().getId());
    }
}
