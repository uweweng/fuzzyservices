/*******************************************************************************
 *
 *  Copyright (C) 2008  Uwe Weng
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
package net.sourceforge.fuzzyservices.swing;

import java.util.ArrayList;

/**
 * DefaultRuleModel
 *
 * @author Uwe Weng
 */
public class DefaultRuleModel extends AbstractRuleModel {

    private OperatorModel certaintyOperator = new DefaultOperatorModel();
    private OperatorModel inferenceOperator = new DefaultOperatorModel();
    private OperatorModel aggregationOperator = new DefaultOperatorModel();
    private float certainty = 1.0f;
    private ArrayList<AntecedentModel> antecedents = new ArrayList<DefaultRuleModel.AntecedentModel>();
    private ArrayList<ConsequentModel> consequents = new ArrayList<DefaultRuleModel.ConsequentModel>();

    @Override
    public OperatorModel getCertaintyOperator() {
        return certaintyOperator;
    }

    @Override
    public OperatorModel getInferenceOperator() {
        return inferenceOperator;
    }

    @Override
    public OperatorModel getAggregationOperator() {
        return aggregationOperator;
    }

    @Override
    public float getCertainty() {
        return certainty;
    }

    @Override
    public void setCertainty(float certainty) {
        this.certainty = certainty;
    }

    @Override
    public int getSizeOfAntecedents() {
        return antecedents.size();
    }

    @Override
    public OperatorModel getCompatibilityOperatorOfAntecedentAt(int index) {
        return antecedents.get(index).compatibilityOperator;
    }

    @Override
    public String getLingVarNameOfAntecedentAt(int index) {
        return antecedents.get(index).lingVarName;
    }

    @Override
    public void setLingVarNameOfAntecedentAt(String name, int index) {
        antecedents.get(index).lingVarName = name;
    }

    @Override
    public String getLingTermNameOfAntecedentAt(int index) {
        return antecedents.get(index).lingTermName;
    }

    @Override
    public void setLingTermNameOfAntecedentAt(String name, int index) {
        antecedents.get(index).lingTermName = name;
    }

    @Override
    public int getSizeOfConsequents() {
        return consequents.size();
    }

    @Override
    public String getLingVarNameOfConsequentAt(int index) {
        return consequents.get(index).lingVarName;
    }

    @Override
    public void setLingVarNameOfConsequentAt(String name, int index) {
        consequents.get(index).lingVarName = name;
    }

    @Override
    public String getLingTermNameOfConsequentAt(int index) {
        return consequents.get(index).lingTermName;
    }

    @Override
    public void setLingTermNameOfConsequentAt(String name, int index) {
        consequents.get(index).lingTermName = name;
    }
    
    private class AntecedentModel {
        public String lingVarName;
        public String lingTermName;
        public OperatorModel compatibilityOperator = new DefaultOperatorModel();
    }
    private class ConsequentModel {
        public String lingVarName;
        public String lingTermName;
    }
}
