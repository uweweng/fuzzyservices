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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import net.sourceforge.fuzzyservices.beans.FuzzyLRInterval;
import net.sourceforge.fuzzyservices.beans.dao.DaoI;
import net.sourceforge.fuzzyservices.beans.dao.FuzzyLRIntervalDao;

/**
 * This class represents fuzzy LR interval beans as resources hosted at the URI path 
 * "/fuzzy_lr_intervals". For instance,
 * 
 * http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_lr_intervals/4711
 * curl -i http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_lr_intervals/4711 -H ’Accept: text/plain’
 *
 * @see FuzzyLRIntervalResource
 * 
 * @version 1.0
 * @author Uwe Weng
 */
@Path("/fuzzy_lr_intervals")
public class FuzzyLRIntervalResources {

    /**
     * Provides both static and dynamic, per-request information, 
     * about the components of a request URI.
     */
    @Context
    UriInfo uriInfo;
    /**
     * The data access object (as data store)
     */
    private DaoI<FuzzyLRInterval, Integer> dao = new FuzzyLRIntervalDao();

    /**
     * Delegates request with sub-path "id" to sub-resource.
     * 
     * @param id the id of the requested sub-resource
     * @return the requested sub-resource
     */
    @Path("{id}")
    public FuzzyLRIntervalResource getFuzzyLRIntervalResourceById(@PathParam("id") int id) {
        FuzzyLRInterval bean = dao.findById(id);
        return new FuzzyLRIntervalResource(bean);
    }

    /**
     * Creates a new resource.
     * 
     * @param newResource the new resource to be created
     * @return the result of the operation as response object with the new 
     * resource as entity
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response createFuzzyLRInterval(FuzzyLRIntervalResource newResource) {
        if ((newResource == null) || (newResource.getBean() == null)) {
            return Response.status(404).build();
        }
        try {
            dao.create(newResource.getBean());
            URI FuzzyLRIntervalUri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newResource.getBean().getId())).build();
            return Response.created(FuzzyLRIntervalUri).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }
}
