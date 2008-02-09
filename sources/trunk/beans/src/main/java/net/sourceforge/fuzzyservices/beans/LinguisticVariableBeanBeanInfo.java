/*
 * LinguisticVariableBeanBeanInfo.java
 *
 * Created on 29. September 2007, 21:52
 */

package net.sourceforge.fuzzyservices.beans;

import java.beans.*;
import net.sourceforge.fuzzyservices.core.FuzzyResourceManager;

/**
 * @author Administrator
 */
public class LinguisticVariableBeanBeanInfo extends SimpleBeanInfo {
    
    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class , null ); // NOI18N
        beanDescriptor.setDisplayName ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"DISPLAY_NAME_LINGUISTIC_VARIABLE") );
        beanDescriptor.setShortDescription ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"SHORT_DESCRIPTION_LINGUISTIC_VARIABLE") );//GEN-HEADEREND:BeanDescriptor
        
        // Here you can add code for customizing the BeanDescriptor.
        
        return beanDescriptor;     }//GEN-LAST:BeanDescriptor
    
    
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_linguisticTerms = 0;
    private static final int PROPERTY_name = 1;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[2];
    
        try {
            properties[PROPERTY_linguisticTerms] = new IndexedPropertyDescriptor ( "linguisticTerms", net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class, "getLinguisticTerms", "setLinguisticTerms", "getLinguisticTerms", "setLinguisticTerms" ); // NOI18N
            properties[PROPERTY_linguisticTerms].setDisplayName ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"DISPLAY_NAME_LINGUISTIC_VARIABLE_LINGUISTIC_TERMS_PROPERTY") );
            properties[PROPERTY_linguisticTerms].setShortDescription ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"SHORT_DESCRIPTION_LINGUISTIC_VARIABLE_LINGUISTIC_TERMS_PROPERTY") );
            properties[PROPERTY_linguisticTerms].setBound ( true );
            properties[PROPERTY_linguisticTerms].setConstrained ( true );
            properties[PROPERTY_linguisticTerms].setPropertyEditorClass ( net.sourceforge.fuzzyservices.beans.LinguisticTermBeanArrayEditor.class );
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_name].setDisplayName ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"DISPLAY_NAME_LINGUISTIC_VARIABLE_NAME_PROPERTY") );
            properties[PROPERTY_name].setShortDescription ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"SHORT_DESCRIPTION_LINGUISTIC_VARIABLE_NAME_PROPERTY") );
            properties[PROPERTY_name].setBound ( true );
            properties[PROPERTY_name].setConstrained ( true );
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
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events
        
        // Here you can add code for customizing the event sets array.
        
        return eventSets;     }//GEN-LAST:Events
    
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_contains0 = 0;
    private static final int METHOD_getLinguisticTerm1 = 1;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[2];
    
        try {
            methods[METHOD_contains0] = new MethodDescriptor ( net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class.getMethod("contains", new Class[] {net.sourceforge.fuzzyservices.beans.LinguisticTermBean.class})); // NOI18N
            methods[METHOD_contains0].setDisplayName ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"DISPLAY_NAME_LINGUISTIC_VARIABLE_CONTAINS") );
            methods[METHOD_contains0].setShortDescription ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"SHORT_DESCRIPTION_LINGUISTIC_VARIABLE_CONTAINS") );
            methods[METHOD_getLinguisticTerm1] = new MethodDescriptor ( net.sourceforge.fuzzyservices.beans.LinguisticVariableBean.class.getMethod("getLinguisticTerm", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getLinguisticTerm1].setDisplayName ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"DISPLAY_NAME_LINGUISTIC_VARIABLE_GET_LINGUISTIC_TERM") );
            methods[METHOD_getLinguisticTerm1].setShortDescription ( FuzzyResourceManager.getString(LinguisticVariableBeanBeanInfo.class,"SHORT_DESCRIPTION_LINGUISTIC_VARIABLE_GET_LINGUISTIC_TERM") );
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

