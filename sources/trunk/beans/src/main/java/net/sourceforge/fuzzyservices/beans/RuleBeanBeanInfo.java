/*
 * RuleBeanBeanInfo.java
 *
 * Created on 20. Oktober 2007, 23:32
 */

package net.sourceforge.fuzzyservices.beans;

import java.beans.*;

/**
 * @author Administrator
 */
public class RuleBeanBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.RuleBean.class , null ); // NOI18N
        beanDescriptor.setHidden ( true );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        
        return beanDescriptor;     }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_aggregationOperator = 0;
    private static final int PROPERTY_antecedents = 1;
    private static final int PROPERTY_certainty = 2;
    private static final int PROPERTY_certaintyOperator = 3;
    private static final int PROPERTY_consequents = 4;
    private static final int PROPERTY_inferenceOperator = 5;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[6];
    
        try {
            properties[PROPERTY_aggregationOperator] = new PropertyDescriptor ( "aggregationOperator", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getAggregationOperator", "setAggregationOperator" ); // NOI18N
            properties[PROPERTY_antecedents] = new IndexedPropertyDescriptor ( "antecedents", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getAntecedents", "setAntecedents", "getAntecedents", "setAntecedents" ); // NOI18N
            properties[PROPERTY_certainty] = new PropertyDescriptor ( "certainty", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getCertainty", "setCertainty" ); // NOI18N
            properties[PROPERTY_certaintyOperator] = new PropertyDescriptor ( "certaintyOperator", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getCertaintyOperator", "setCertaintyOperator" ); // NOI18N
            properties[PROPERTY_consequents] = new IndexedPropertyDescriptor ( "consequents", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getConsequents", "setConsequents", "getConsequents", "setConsequents" ); // NOI18N
            properties[PROPERTY_inferenceOperator] = new PropertyDescriptor ( "inferenceOperator", net.sourceforge.fuzzyservices.beans.RuleBean.class, "getInferenceOperator", "setInferenceOperator" ); // NOI18N
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
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.RuleBean.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;     }//GEN-LAST:Events
    
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_vetoableChange0 = 0;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[1];
    
        try {
            methods[METHOD_vetoableChange0] = new MethodDescriptor ( net.sourceforge.fuzzyservices.beans.RuleBean.class.getMethod("vetoableChange", new Class[] {java.beans.PropertyChangeEvent.class})); // NOI18N
            methods[METHOD_vetoableChange0].setDisplayName ( "" );
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

