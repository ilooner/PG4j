/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topekalabs.pg4j.parse;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Topeka Labs
 */
public class ValidationPayload
{
    private Set<TypeParamCombination> typeParamCombinations;
    private String classRenameRegex;
    
    public ValidationPayload(Set<TypeParamCombination> typeParamCombinations,
                             String classNameRegex)
    {
        setTypeParamCombinations(typeParamCombinations);
        setClassRenameRegex(classNameRegex);
    }

    /**
     * @return the typeParamCombinations
     */
    public Set<TypeParamCombination> getTypeParamCombinations()
    {
        return Collections.unmodifiableSet(typeParamCombinations);
    }

    /**
     * @param typeParamCombinations the typeParamCombinations to set
     */
    private void setTypeParamCombinations(Set<TypeParamCombination> typeParamCombinations)
    {
        Preconditions.checkNotNull(typeParamCombinations);
        this.typeParamCombinations = typeParamCombinations;
    }

    /**
     * @return the classNameRegex
     */
    public String getClassRenameRegex()
    {
        return classRenameRegex;
    }

    /**
     * @param classNameRegex the classNameRegex to set
     */
    private void setClassRenameRegex(String classNameRegex)
    {
        Preconditions.checkNotNull(classNameRegex);
        this.classRenameRegex = classNameRegex;
    }
}
