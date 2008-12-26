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
 *
 * @author Uwe Weng
 */
public class FuzzyCalculatorBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[0];//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_add0 = 0;
    private static final int METHOD_add1 = 1;
    private static final int METHOD_add2 = 2;
    private static final int METHOD_add3 = 3;
    private static final int METHOD_divide4 = 4;
    private static final int METHOD_divide5 = 5;
    private static final int METHOD_divide6 = 6;
    private static final int METHOD_divide7 = 7;
    private static final int METHOD_multiply8 = 8;
    private static final int METHOD_multiply9 = 9;
    private static final int METHOD_multiply10 = 10;
    private static final int METHOD_multiply11 = 11;
    private static final int METHOD_subtract12 = 12;
    private static final int METHOD_subtract13 = 13;
    private static final int METHOD_subtract14 = 14;
    private static final int METHOD_subtract15 = 15;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[16];
    
        try {
            methods[METHOD_add0] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("add", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyInterval.class})); // NOI18N
            methods[METHOD_add0].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_ADD_FUZZY_INTERVAL_METHOD") );
            methods[METHOD_add0].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_ADD_FUZZY_INTERVAL_METHOD") );
            methods[METHOD_add1] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("add", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class})); // NOI18N
            methods[METHOD_add1].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_ADD_FUZZY_LR_INTERVAL_METHOD") );
            methods[METHOD_add1].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_ADD_FUZZY_LR_INTERVAL_METHOD") );
            methods[METHOD_add2] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("add", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class})); // NOI18N
            methods[METHOD_add2].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_ADD_FUZZY_LR_NUMBER_METHOD") );
            methods[METHOD_add2].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_ADD_FUZZY_LR_NUMBER_METHOD") );
            methods[METHOD_add3] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("add", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyNumber.class})); // NOI18N
            methods[METHOD_add3].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_ADD_FUZZY_NUMBER_METHOD") );
            methods[METHOD_add3].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_ADD_FUZZY_NUMBER_METHOD") );
            methods[METHOD_divide4] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("divide", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyInterval.class})); // NOI18N
            methods[METHOD_divide4].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_DIVIDE_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_divide4].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_DIVIDE_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_divide5] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("divide", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class})); // NOI18N
            methods[METHOD_divide5].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_DIVIDE_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_divide5].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_DIVIDE_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_divide6] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("divide", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class})); // NOI18N
            methods[METHOD_divide6].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_DIVIDE_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_divide6].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_DIVIDE_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_divide7] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("divide", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyNumber.class})); // NOI18N
            methods[METHOD_divide7].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_DIVIDE_FUZZY_NUMBER_METHOD")  );
            methods[METHOD_divide7].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_DIVIDE_FUZZY_NUMBER_METHOD")  );
            methods[METHOD_multiply8] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("multiply", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyInterval.class})); // NOI18N
            methods[METHOD_multiply8].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_MULTIPLY_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_multiply8].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_MULTIPLY_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_multiply9] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("multiply", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class})); // NOI18N
            methods[METHOD_multiply9].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_MULTIPLY_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_multiply9].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_MULTIPLY_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_multiply10] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("multiply", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class})); // NOI18N
            methods[METHOD_multiply10].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_MULTIPLY_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_multiply10].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_MULTIPLY_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_multiply11] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("multiply", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyNumber.class})); // NOI18N
            methods[METHOD_multiply11].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_MULTIPLY_FUZZY_NUMBER_METHOD")  );
            methods[METHOD_multiply11].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_MULTIPLY_FUZZY_NUMBER_METHOD")  );
            methods[METHOD_subtract12] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("subtract", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyInterval.class})); // NOI18N
            methods[METHOD_subtract12].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_SUBTRACT_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_subtract12].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_SUBTRACT_FUZZY_INTERVAL_METHOD")  );
            methods[METHOD_subtract13] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("subtract", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class, net.sourceforge.fuzzyservices.beans.FuzzyLRInterval.class})); // NOI18N
            methods[METHOD_subtract13].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_SUBTRACT_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_subtract13].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_SUBTRACT_FUZZY_LR_INTERVAL_METHOD")  );
            methods[METHOD_subtract14] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("subtract", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyLRNumber.class})); // NOI18N
            methods[METHOD_subtract14].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_SUBTRACT_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_subtract14].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_SUBTRACT_FUZZY_LR_NUMBER_METHOD")  );
            methods[METHOD_subtract15] = new MethodDescriptor(net.sourceforge.fuzzyservices.beans.FuzzyCalculator.class.getMethod("subtract", new Class[] {net.sourceforge.fuzzyservices.beans.FuzzyNumber.class, net.sourceforge.fuzzyservices.beans.FuzzyNumber.class})); // NOI18N
            methods[METHOD_subtract15].setDisplayName ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("DISPLAY_NAME_FUZZY_CALCULATOR_SUBTRACT_FUZZY_NUMBER_METHOD")  );
            methods[METHOD_subtract15].setShortDescription ( java.util.ResourceBundle.getBundle("net/sourceforge/fuzzyservices/beans/resources").getString("SHORT_DESCRIPTION_FUZZY_CALCULATOR_SUBTRACT_FUZZY_NUMBER_METHOD")  );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.
    
        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

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

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch ( iconKind ) {
        case ICON_COLOR_16x16:
            if ( iconNameC16 == null )
                return null;
            else {
                if( iconColor16 == null )
                    iconColor16 = loadImage( iconNameC16 );
                return iconColor16;
            }
        case ICON_COLOR_32x32:
            if ( iconNameC32 == null )
                return null;
            else {
                if( iconColor32 == null )
                    iconColor32 = loadImage( iconNameC32 );
                return iconColor32;
            }
        case ICON_MONO_16x16:
            if ( iconNameM16 == null )
                return null;
            else {
                if( iconMono16 == null )
                    iconMono16 = loadImage( iconNameM16 );
                return iconMono16;
            }
        case ICON_MONO_32x32:
            if ( iconNameM32 == null )
                return null;
            else {
                if( iconMono32 == null )
                    iconMono32 = loadImage( iconNameM32 );
                return iconMono32;
            }
	default: return null;
        }
    }

}

