/*
 * Copyright 2016 Federico Tomassetti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.javaparser.symbolsolver.reflectionmodel;

import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

/**
 * @author Federico Tomassetti
 */
public class ReflectionParameterDeclaration implements ResolvedParameterDeclaration {
    private Class<?> type;
    private java.lang.reflect.Type genericType;
    private TypeSolver typeSolver;
    private boolean variadic;
    private String name;

    /**
     *
     * @param type
     * @param genericType
     * @param typeSolver
     * @param variadic
     * @param name can potentially be null
     */
    public ReflectionParameterDeclaration(Class<?> type, java.lang.reflect.Type genericType, TypeSolver typeSolver,
                                          boolean variadic, String name) {
        this.type = type;
        this.genericType = genericType;
        this.typeSolver = typeSolver;
        this.variadic = variadic;
        this.name = name;
    }

    @Override
    public String getName() {
        if (hasName()) {
            return this.name;
        } else {
            throw new IllegalStateException("Name of parameters obtained through reflection can be not present");
        }
    }

    @Override
    public boolean hasName() {
        return name != null;
    }

    @Override
    public String toString() {
        return "ReflectionParameterDeclaration{" +
                "type=" + type +
                '}';
    }

    @Override
    public boolean isField() {
        return false;
    }

    @Override
    public boolean isParameter() {
        return true;
    }

    @Override
    public boolean isVariadic() {
        return variadic;
    }

    @Override
    public boolean isType() {
        return false;
    }

    @Override
    public ResolvedType getType() {
        return ReflectionFactory.typeUsageFor(genericType, typeSolver);
    }
}
