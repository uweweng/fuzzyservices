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
package net.sourceforge.fuzzyservices.beans.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * This interface defines Data Access Objects that handle the reading and 
 * writing a class from a data store (e.g. database).
 *
 * @version 1.0
 * @author Uwe Weng
 */
public interface DaoI<T, ID extends Serializable> extends Serializable {

    /**
     * Retrieves an object associated with a specific ID.
     * 
     * @param id Identifier that matches a specific row in the database to find and return.
     * @return The object that has the ID field which equals id or null if no matches.
     */
    public T findById(final ID id);

    /**
     * Query for all of the items in the object table. For medium sized or large tables, this may load a lot of objects
     * into memory.
     * 
     * @return A list of all of the objects in the table.
     * @see #iterate(int, int)
     */
    public List<T> findAll();

    /**
     * Gets an iterator with <code>max</code> objects from <code>offset</code>.
     * 
     * @param offset start position for reading objects from database ordered by id.
     * @param max maximum count of objects.
     * @return An iterator with limited count of objects.
     */
    public Iterable<T> iterate(int offset, int max);

    /**
     * Counts the entities.
     * 
     * @return number of rows / entities in the table.
     */
    public int size();

    /**
     * Creates a new row in the database from an object.
     * 
     * @param data The data item that we are creating in the database.
     * @throws EntityExistsException if the entity already exists.
     */
    public void create(T data) throws EntityExistsException;

    /**
     * Saves the fields from an object to the database. If you have made changes to an object, this is how you persist
     * those changes to the database. You cannot use this method to update the id field.
     * 
     * @param data The data item that we are updating in the database.
     * @return the updated and attached entity
     * @throws EntityNotFoundException if the entity no longer exists in database.
     */
    public T update(T data) throws EntityNotFoundException;

    /**
     * Deletes an object from the database.
     * 
     * @param data The data item that we are deleting from the database.
     * @throws EntityNotFoundException if the entity no longer exists in database.
     */
    public void remove(T data) throws EntityNotFoundException;

    /**
     * Deletes an identified object from the database.
     * 
     * @param id The identifier of the deleting data item.
     * @throws EntityNotFoundException if the entity no longer exists in database.
     */
    public void removeById(final ID id) throws EntityNotFoundException;
}
