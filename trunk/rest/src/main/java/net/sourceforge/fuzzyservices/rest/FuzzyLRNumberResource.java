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
 *  GNU General Public License for more bean.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Fuzzy Services; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  The license is also available at http://www.gnu.org/licenses/gpl.txt
 *
 ******************************************************************************/
package net.sourceforge.fuzzyservices.rest;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import net.sourceforge.fuzzyservices.beans.FuzzyLRNumber;
import net.sourceforge.fuzzyservices.beans.dao.DaoI;
import net.sourceforge.fuzzyservices.beans.dao.FuzzyLRNumberDao;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a fuzzy LR number bean as resource hosted at the URI 
 * path "/id". JAXB is used for serialisation.
 * 
 * http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_lr_numbers
 * curl -i http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_lr_numbers -H ’Accept: text/plain’
 *
 * @version 1.0
 * @author Uwe Weng
 */
@XmlRootElement
public class FuzzyLRNumberResource {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The managed bean representing as resource
     */
    protected FuzzyLRNumber bean;
    /**
     * The Data Access Object
     */
    @XmlTransient
    protected DaoI<FuzzyLRNumber, Integer> dao = new FuzzyLRNumberDao();

    /**
     * Default constructor
     */
    public FuzzyLRNumberResource() {
    }

    /**
     * Constructor with the managing bean as parameter.
     * 
     * @param bean the managing bean
     */
    public FuzzyLRNumberResource(FuzzyLRNumber bean) {
        this.bean = bean;
    }

    /**
     * Returns the managing bean.
     * 
     * @return the bean of the managed resource
     */
    public FuzzyLRNumber getBean() {
        return bean;
    }

    /**
     * Sets the managing bean.
     * 
     * @param bean the bean to set
     */
    public void setBean(FuzzyLRNumber bean) {
        this.bean = bean;
    }

    /**
     * Returns the resource identified by <code>id</code>.
     * 
     * @param id the identifier of the bean
     * @return the status of the operation containing this resource with the bean
     */
    @GET
    public Response get(@PathParam("id") int id) {
        if (bean == null) {
            return Response.status(404).build();
        }
        return Response.ok(this).build();
    }

    /**
     * Updates an existing resource. Note that the ID in the bean passed
     * as args must be the same as that which was passed as args in the URL.
     *
     * @param resource the resource to be updated
     * @return the status of the operation.
     */
    @PUT
    public Response put(FuzzyLRNumberResource resource) {
        try {
            this.bean = dao.update(resource.getBean());
            return Response.ok(this).build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * Removes the bean from the data store.
     * 
     * @param id the identifier of the bean
     * @return the status of the operation
     */
    @DELETE
    public Response delete(@PathParam("id") int id) {
        try {
            dao.removeById(id);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        FuzzyLRNumberResource res = (FuzzyLRNumberResource) obj;

        return new EqualsBuilder().append(this.bean, res.bean).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 21).append(this.bean).toHashCode();
    }

    @Override
    public String toString() {
        return (bean != null) ? bean.toString() : "bean = null";
    }
}
