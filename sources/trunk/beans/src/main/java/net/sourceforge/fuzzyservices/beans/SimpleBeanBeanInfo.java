/*
 * SimpleBeanBeanInfo.java
 *
 * Created on 13. Oktober 2007, 17:40
 */
package net.sourceforge.fuzzyservices.beans;

import java.beans.*;


/**
 * @author Administrator
 */
public class SimpleBeanBeanInfo extends SimpleBeanInfo {
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_attr = 0;
    private static final int PROPERTY_simpleAttribute = 1;
    private static final int PROPERTY_simpleAttrs = 2;
    private static final int PROPERTY_text = 3;

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_propertyChangeListener = 0;
    private static final int EVENT_vetoableChangeListener = 1;
    private static final int defaultPropertyIndex = -1; //GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1; //GEN-END:Idx

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor() {
        BeanDescriptor beanDescriptor = new BeanDescriptor(net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                null); // NOI18N//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.
        return beanDescriptor;
    } //GEN-LAST:BeanDescriptor

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor() {
        PropertyDescriptor[] properties = new PropertyDescriptor[4];

        try {
            properties[PROPERTY_attr] = new IndexedPropertyDescriptor("attr",
                    net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "getAttr", "setAttr", "getAttr", "setAttr"); // NOI18N
            properties[PROPERTY_attr].setBound(true);
            properties[PROPERTY_attr].setConstrained(true);
            properties[PROPERTY_simpleAttribute] = new PropertyDescriptor("simpleAttribute",
                    net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "getSimpleAttribute", "setSimpleAttribute"); // NOI18N
            properties[PROPERTY_simpleAttribute].setShortDescription("Test");
            properties[PROPERTY_simpleAttribute].setPropertyEditorClass(net.sourceforge.fuzzyservices.beans.SimpleAttributeEditor.class);
            properties[PROPERTY_simpleAttrs] = new IndexedPropertyDescriptor("simpleAttrs",
                    net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "getSimpleAttrs", "setSimpleAttrs", "getSimpleAttrs",
                    "setSimpleAttrs"); // NOI18N
            properties[PROPERTY_simpleAttrs].setBound(true);
            properties[PROPERTY_simpleAttrs].setConstrained(true);
            properties[PROPERTY_text] = new PropertyDescriptor("text",
                    net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "getText", "setText"); // NOI18N
            properties[PROPERTY_text].setBound(true);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } //GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.
        return properties;
    } //GEN-LAST:Properties

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor() {
        EventSetDescriptor[] eventSets = new EventSetDescriptor[2];

        try {
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor(net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "propertyChangeListener",
                    java.beans.PropertyChangeListener.class,
                    new String[] { "propertyChange" },
                    "addPropertyChangeListener", "removePropertyChangeListener"); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor(net.sourceforge.fuzzyservices.beans.SimpleBean.class,
                    "vetoableChangeListener",
                    java.beans.VetoableChangeListener.class,
                    new String[] { "vetoableChange" },
                    "addVetoableChangeListener", "removeVetoableChangeListener"); // NOI18N
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } //GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.
        return eventSets;
    } //GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor() {
        MethodDescriptor[] methods = new MethodDescriptor[0]; //GEN-HEADEREND:Methods

        // Here you can add code for customizing the methods array.
        return methods;
    } //GEN-LAST:Methods

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
     *                 returned by getPropertyDescriptors.
     * <P>        Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean.
     * @return Index of default event in the EventSetDescriptor array
     *                returned by getEventSetDescriptors.
     * <P>        Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }
}
