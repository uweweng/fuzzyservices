/*******************************************************************************
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
package net.sourceforge.fuzzyservices.beans;

import java.beans.*;

/**
 * @author Uwe Weng
 */
public class MembershipFunctionBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.MembershipFunction.class , null ); // NOI18N
        beanDescriptor.setHidden ( true );//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_alpha = 0;
    private static final int PROPERTY_beta = 1;
    private static final int PROPERTY_convex = 2;
    private static final int PROPERTY_defined = 3;
    private static final int PROPERTY_height = 4;
    private static final int PROPERTY_maxDefinedX = 5;
    private static final int PROPERTY_minDefinedX = 6;
    private static final int PROPERTY_normalized = 7;
    private static final int PROPERTY_XValues = 8;

    // Property array
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[9];

        try {
            properties[PROPERTY_alpha] = new PropertyDescriptor ( "alpha", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getAlpha", null ); // NOI18N
            properties[PROPERTY_beta] = new PropertyDescriptor ( "beta", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getBeta", null ); // NOI18N
            properties[PROPERTY_convex] = new PropertyDescriptor ( "convex", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "isConvex", null ); // NOI18N
            properties[PROPERTY_defined] = new PropertyDescriptor ( "defined", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "isDefined", null ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_maxDefinedX] = new PropertyDescriptor ( "maxDefinedX", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getMaxDefinedX", null ); // NOI18N
            properties[PROPERTY_minDefinedX] = new PropertyDescriptor ( "minDefinedX", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getMinDefinedX", null ); // NOI18N
            properties[PROPERTY_normalized] = new PropertyDescriptor ( "normalized", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "isNormalized", null ); // NOI18N
            properties[PROPERTY_XValues] = new PropertyDescriptor ( "XValues", net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "getXValues", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_propertyChangeListener = 0;
    private static final int EVENT_vetoableChangeListener = 1;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[2];

        try {
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.MembershipFunction.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_clear0 = 0;
    private static final int METHOD_clone1 = 1;
    private static final int METHOD_concentrate2 = 2;
    private static final int METHOD_dilate3 = 3;
    private static final int METHOD_equals4 = 4;
    private static final int METHOD_getDegreeOfMembership5 = 5;
    private static final int METHOD_getGranularity6 = 6;
    private static final int METHOD_getNumSteps7 = 7;
    private static final int METHOD_getSlope8 = 8;
    private static final int METHOD_hashCode9 = 9;
    private static final int METHOD_invert10 = 10;
    private static final int METHOD_iterator11 = 11;
    private static final int METHOD_negate12 = 12;
    private static final int METHOD_normalize13 = 13;
    private static final int METHOD_reciproce14 = 14;
    private static final int METHOD_reduce15 = 15;
    private static final int METHOD_remove16 = 16;
    private static final int METHOD_setDegreeOfMembership17 = 17;
    private static final int METHOD_size18 = 18;
    private static final int METHOD_toString19 = 19;

    // Method array
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[20];

        try {
            methods[METHOD_clear0] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("clear", new Class[] {})); // NOI18N
            methods[METHOD_clear0].setDisplayName ( "" );
            methods[METHOD_clone1] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("clone", new Class[] {})); // NOI18N
            methods[METHOD_clone1].setDisplayName ( "" );
            methods[METHOD_concentrate2] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("concentrate", new Class[] {})); // NOI18N
            methods[METHOD_concentrate2].setDisplayName ( "" );
            methods[METHOD_dilate3] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("dilate", new Class[] {})); // NOI18N
            methods[METHOD_dilate3].setDisplayName ( "" );
            methods[METHOD_equals4] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("equals", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_equals4].setDisplayName ( "" );
            methods[METHOD_getDegreeOfMembership5] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("getDegreeOfMembership", new Class[] {Float.class})); // NOI18N
            methods[METHOD_getDegreeOfMembership5].setDisplayName ( "" );
            methods[METHOD_getGranularity6] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("getGranularity", new Class[] {net.sourceforge.fuzzyservices.beans.MembershipFunction.class})); // NOI18N
            methods[METHOD_getGranularity6].setDisplayName ( "" );
            methods[METHOD_getNumSteps7] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("getNumSteps", new Class[] {net.sourceforge.fuzzyservices.beans.MembershipFunction.class})); // NOI18N
            methods[METHOD_getNumSteps7].setDisplayName ( "" );
            methods[METHOD_getSlope8] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("getSlope", new Class[] {Integer.class, Integer.class})); // NOI18N
            methods[METHOD_getSlope8].setDisplayName ( "" );
            methods[METHOD_hashCode9] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("hashCode", new Class[] {})); // NOI18N
            methods[METHOD_hashCode9].setDisplayName ( "" );
            methods[METHOD_invert10] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("invert", new Class[] {})); // NOI18N
            methods[METHOD_invert10].setDisplayName ( "" );
            methods[METHOD_iterator11] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("iterator", new Class[] {})); // NOI18N
            methods[METHOD_iterator11].setDisplayName ( "" );
            methods[METHOD_negate12] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("negate", new Class[] {})); // NOI18N
            methods[METHOD_negate12].setDisplayName ( "" );
            methods[METHOD_normalize13] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("normalize", new Class[] {})); // NOI18N
            methods[METHOD_normalize13].setDisplayName ( "" );
            methods[METHOD_reciproce14] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("reciproce", new Class[] {})); // NOI18N
            methods[METHOD_reciproce14].setDisplayName ( "" );
            methods[METHOD_reduce15] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("reduce", new Class[] {})); // NOI18N
            methods[METHOD_reduce15].setDisplayName ( "" );
            methods[METHOD_remove16] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("remove", new Class[] {Float.class})); // NOI18N
            methods[METHOD_remove16].setDisplayName ( "" );
            methods[METHOD_setDegreeOfMembership17] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("setDegreeOfMembership", new Class[] {Float.class, Float.class})); // NOI18N
            methods[METHOD_setDegreeOfMembership17].setDisplayName ( "" );
            methods[METHOD_size18] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("size", new Class[] {})); // NOI18N
            methods[METHOD_size18].setDisplayName ( "" );
            methods[METHOD_toString19] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.MembershipFunction.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString19].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods


    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx


//GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass

    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
	return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return  An array of EventSetDescriptors describing the kinds of
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return  An array of MethodDescriptors describing the methods
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean.
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }
}

