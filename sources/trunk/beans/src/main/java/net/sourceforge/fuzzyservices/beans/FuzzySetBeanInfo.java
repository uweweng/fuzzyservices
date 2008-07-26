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
public class FuzzySetBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.FuzzySet.class , null ); // NOI18N
        beanDescriptor.setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET") );
        beanDescriptor.setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET") );//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_convex = 0;
    private static final int PROPERTY_height = 1;
    private static final int PROPERTY_membershipFunction = 2;
    private static final int PROPERTY_normalized = 3;
    private static final int PROPERTY_validFuzzyInterval = 4;
    private static final int PROPERTY_validFuzzyLRInterval = 5;
    private static final int PROPERTY_validFuzzyLRNumber = 6;
    private static final int PROPERTY_validFuzzyNumber = 7;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[8];
    
        try {
            properties[PROPERTY_convex] = new PropertyDescriptor ( "convex", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isConvex", null ); // NOI18N
            properties[PROPERTY_convex].setExpert ( true );
            properties[PROPERTY_convex].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_CONVEX_PROPERTY") );
            properties[PROPERTY_convex].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_CONVEX_PROPERTY") );
            properties[PROPERTY_convex].setBound ( true );
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_height].setExpert ( true );
            properties[PROPERTY_height].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_HEIGHT_PROPERTY") );
            properties[PROPERTY_height].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_HEIGHT_PROPERTY") );
            properties[PROPERTY_height].setBound ( true );
            properties[PROPERTY_membershipFunction] = new PropertyDescriptor ( "membershipFunction", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "getMembershipFunction", "setMembershipFunction" ); // NOI18N
            properties[PROPERTY_membershipFunction].setPreferred ( true );
            properties[PROPERTY_membershipFunction].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_MEMBERSHIP_FUNCTION_PROPERTY") );
            properties[PROPERTY_membershipFunction].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_MEMBERSHIP_FUNCTION_PROPERTY") );
            properties[PROPERTY_membershipFunction].setBound ( true );
            properties[PROPERTY_membershipFunction].setPropertyEditorClass ( MembershipFunctionEditor.class );
            properties[PROPERTY_normalized] = new PropertyDescriptor ( "normalized", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isNormalized", null ); // NOI18N
            properties[PROPERTY_normalized].setExpert ( true );
            properties[PROPERTY_normalized].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_NORMALIZED_PROPERTY") );
            properties[PROPERTY_normalized].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_NORMALIZED_PROPERTY") );
            properties[PROPERTY_normalized].setBound ( true );
            properties[PROPERTY_validFuzzyInterval] = new PropertyDescriptor ( "validFuzzyInterval", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isValidFuzzyInterval", null ); // NOI18N
            properties[PROPERTY_validFuzzyInterval].setExpert ( true );
            properties[PROPERTY_validFuzzyInterval].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_VALID_FUZZY_INTERVAL_PROPERTY") );
            properties[PROPERTY_validFuzzyInterval].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_VALID_FUZZY_INTERVAL_PROPERTY") );
            properties[PROPERTY_validFuzzyInterval].setBound ( true );
            properties[PROPERTY_validFuzzyLRInterval] = new PropertyDescriptor ( "validFuzzyLRInterval", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isValidFuzzyLRInterval", null ); // NOI18N
            properties[PROPERTY_validFuzzyLRInterval].setExpert ( true );
            properties[PROPERTY_validFuzzyLRInterval].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_VALID_FUZZY_LR_INTERVAL_PROPERTY") );
            properties[PROPERTY_validFuzzyLRInterval].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_VALID_FUZZY_LR_INTERVAL_PROPERTY") );
            properties[PROPERTY_validFuzzyLRInterval].setBound ( true );
            properties[PROPERTY_validFuzzyLRNumber] = new PropertyDescriptor ( "validFuzzyLRNumber", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isValidFuzzyLRNumber", null ); // NOI18N
            properties[PROPERTY_validFuzzyLRNumber].setExpert ( true );
            properties[PROPERTY_validFuzzyLRNumber].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_VALID_FUZZY_LR_NUMBER_PROPERTY") );
            properties[PROPERTY_validFuzzyLRNumber].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_VALID_FUZZY_LR_NUMBER_PROPERTY") );
            properties[PROPERTY_validFuzzyLRNumber].setBound ( true );
            properties[PROPERTY_validFuzzyNumber] = new PropertyDescriptor ( "validFuzzyNumber", net.sourceforge.fuzzyservices.beans.FuzzySet.class, "isValidFuzzyNumber", null ); // NOI18N
            properties[PROPERTY_validFuzzyNumber].setExpert ( true );
            properties[PROPERTY_validFuzzyNumber].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_VALID_FUZZY_NUMBER_PROPERTY") );
            properties[PROPERTY_validFuzzyNumber].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_VALID_FUZZY_NUMBER_PROPERTY") );
            properties[PROPERTY_validFuzzyNumber].setBound ( true );
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_propertyChangeListener = 0;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[1];
    
        try {
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.FuzzySet.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_concentrate0 = 0;
    private static final int METHOD_dilate1 = 1;
    private static final int METHOD_normalize2 = 2;
    private static final int METHOD_reciproce3 = 3;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[4];
    
        try {
            methods[METHOD_concentrate0] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzySet.class.getMethod("concentrate", new Class[] {})); // NOI18N
            methods[METHOD_concentrate0].setExpert ( true );
            methods[METHOD_concentrate0].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_CONCENTRATE_METHOD") );
            methods[METHOD_concentrate0].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_CONCENTRATE_METHOD") );
            methods[METHOD_dilate1] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzySet.class.getMethod("dilate", new Class[] {})); // NOI18N
            methods[METHOD_dilate1].setExpert ( true );
            methods[METHOD_dilate1].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_DILATE_METHOD") );
            methods[METHOD_dilate1].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_DILATE_METHOD") );
            methods[METHOD_normalize2] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzySet.class.getMethod("normalize", new Class[] {})); // NOI18N
            methods[METHOD_normalize2].setExpert ( true );
            methods[METHOD_normalize2].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_NORMALIZE_METHOD") );
            methods[METHOD_normalize2].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_NORMALIZE_METHOD") );
            methods[METHOD_reciproce3] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzySet.class.getMethod("reciproce", new Class[] {})); // NOI18N
            methods[METHOD_reciproce3].setExpert ( true );
            methods[METHOD_reciproce3].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_SET_RECIPROCE_METHOD") );
            methods[METHOD_reciproce3].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_SET_RECIPROCE_METHOD") );
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

