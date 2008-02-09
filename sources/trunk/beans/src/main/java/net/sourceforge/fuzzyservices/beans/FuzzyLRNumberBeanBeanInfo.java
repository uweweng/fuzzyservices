/*
 * FuzzyLRNumberBeanBeanInfo.java
 *
 * Created on 29. September 2007, 22:20
 */

package net.sourceforge.fuzzyservices.beans;

import java.beans.*;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;

/**
 * @author Administrator
 */
public class FuzzyLRNumberBeanBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class , null ); // NOI18N
        beanDescriptor.setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER") );
        beanDescriptor.setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER") );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        
        return beanDescriptor;     }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_alpha = 0;
    private static final int PROPERTY_beta = 1;
    private static final int PROPERTY_membershipFunction = 2;
    private static final int PROPERTY_negative = 3;
    private static final int PROPERTY_positive = 4;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[5];
    
        try {
            properties[PROPERTY_alpha] = new PropertyDescriptor ( "alpha", net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "getAlpha", null ); // NOI18N
            properties[PROPERTY_alpha].setExpert ( true );
            properties[PROPERTY_alpha].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_ALPHA_PROPERTY") );
            properties[PROPERTY_alpha].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_ALPHA_PROPERTY") );
            properties[PROPERTY_alpha].setBound ( true );
            properties[PROPERTY_beta] = new PropertyDescriptor ( "beta", net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "getBeta", null ); // NOI18N
            properties[PROPERTY_beta].setExpert ( true );
            properties[PROPERTY_beta].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_BETA_PROPERTY") );
            properties[PROPERTY_beta].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_BETA_PROPERTY") );
            properties[PROPERTY_beta].setBound ( true );
            properties[PROPERTY_membershipFunction] = new IndexedPropertyDescriptor ( "membershipFunction", net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "getMembershipFunction", "setMembershipFunction", "getMembershipFunction", "setMembershipFunction" ); // NOI18N
            properties[PROPERTY_membershipFunction].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_MEMBERSHIP_FUNCTION_PROPERTY") );
            properties[PROPERTY_membershipFunction].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_MEMBERSHIP_FUNCTION_PROPERTY") );
            properties[PROPERTY_membershipFunction].setBound ( true );
            properties[PROPERTY_membershipFunction].setPropertyEditorClass ( net.sourceforge.fuzzyservices.beans.MembershipFunctionPointBeanArrayEditor.class );
            properties[PROPERTY_negative] = new PropertyDescriptor ( "negative", net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "isNegative", null ); // NOI18N
            properties[PROPERTY_negative].setExpert ( true );
            properties[PROPERTY_negative].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_NEGATIVE_PROPERTY") );
            properties[PROPERTY_negative].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_NEGATIVE_PROPERTY") );
            properties[PROPERTY_negative].setBound ( true );
            properties[PROPERTY_positive] = new PropertyDescriptor ( "positive", net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "isPositive", null ); // NOI18N
            properties[PROPERTY_positive].setExpert ( true );
            properties[PROPERTY_positive].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_POSITIVE_PROPERTY") );
            properties[PROPERTY_positive].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_POSITIVE_PROPERTY") );
            properties[PROPERTY_positive].setBound ( true );
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
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;     }//GEN-LAST:Events
    
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_invert0 = 0;
    private static final int METHOD_negate1 = 1;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[2];
    
        try {
            methods[METHOD_invert0] = new MethodDescriptor ( net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class.getMethod("invert", new Class[] {})); // NOI18N
            methods[METHOD_invert0].setExpert ( true );
            methods[METHOD_invert0].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_INVERT_METHOD") );
            methods[METHOD_invert0].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_INVERT_METHOD") );
            methods[METHOD_negate1] = new MethodDescriptor ( net.sourceforge.fuzzyservices.beans.FuzzyLRNumberBean.class.getMethod("negate", new Class[] {})); // NOI18N
            methods[METHOD_negate1].setExpert ( true );
            methods[METHOD_negate1].setDisplayName ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"DISPLAY_NAME_FUZZY_LR_NUMBER_NEGATE_METHOD") );
            methods[METHOD_negate1].setShortDescription ( FuzzyResourceManager.getString(FuzzyLRNumberBeanBeanInfo.class,"SHORT_DESCRIPTION_FUZZY_LR_NUMBER_NEGATE_METHOD") );
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

