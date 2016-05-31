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

import javax.xml.bind.annotation.XmlRootElement;
import net.sourceforge.fuzzyservices.beans.FuzzyNumber;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This class represents a fuzzy number bean as resource hosted at the URI path 
 * "/id". JAXB is used for serialisation.
 * 
 * http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_numbers
 * curl -i http://localhost:8080/net/sourceforge/fuzzyservices/fuzzy_numbers -H ’Accept: text/plain’
 *
 * @version 1.0
 * @author Uwe Weng
 */
@XmlRootElement
public class FuzzyNumberResource {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The managed bean representing as resource
     */
    protected FuzzyNumber bean;

    /**
     * Default constructor
     */
    public FuzzyNumberResource() {
    }

    /**
     * Constructor with the managing bean as parameter.
     * 
     * @param bean the managing bean
     */
    public FuzzyNumberResource(FuzzyNumber bean) {
        this.bean = bean;
    }

    /**
     * Returns the managing bean.
     * 
     * @return the bean of the managed resource
     */
    public FuzzyNumber getBean() {
        return bean;
    }

    /**
     * Sets the managing bean.
     * 
     * @param bean the bean to set
     */
    public void setBean(FuzzyNumber bean) {
        this.bean = bean;
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
        FuzzyNumberResource res = (FuzzyNumberResource) obj;

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
