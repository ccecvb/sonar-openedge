/*******************************************************************************
 * Copyright (c) 2003-2015 John Green
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    John Green - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.prorefactor.treeparser.symbols;

import org.prorefactor.core.JPNode;
import org.prorefactor.core.NodeTypes;
import org.prorefactor.treeparser.ClassSupport;
import org.prorefactor.treeparser.DataType;
import org.prorefactor.treeparser.Primative;
import org.prorefactor.treeparser.TreeParserSymbolScope;
import org.prorefactor.treeparser.Value;

/**
 * A Symbol defined with DEFINE VARIABLE or any of the other various syntaxes which implicitly define a variable.
 */
public class Variable extends Symbol implements Primative, Value {

  private int extent;
  private DataType dataType;
  private Object value;
  private String className = null;
  private boolean refInFrame = false;

  public Variable(String name, TreeParserSymbolScope scope) {
    super(name, scope);
  }

  public Variable(String name, TreeParserSymbolScope scope, boolean parameter) {
    super(name, scope, parameter);
  }

  @Override
  public void assignAttributesLike(Primative likePrim) {
    dataType = likePrim.getDataType();
    className = likePrim.getClassName();
    extent = likePrim.getExtent();
  }

  /**
   * Return the name of the variable. For this subclass of Symbol, fullName() returns the same value as getName().
   */
  @Override
  public String fullName() {
    return getName();
  }

  @Override
  public String getClassName() {
    return className;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public int getExtent() {
    return extent;
  }

  @Override
  public Object getValue() {
    return value;
  }

  /**
   * Returns NodeTypes.VARIABLE
   */
  @Override
  public int getProgressType() {
    return NodeTypes.VARIABLE;
  }

  @Override
  public Primative setClassName(String s) {
    this.className = s;
    return this;
  }

  @Override
  public Primative setClassName(JPNode typeNameNode) {
    this.className = ClassSupport.qualifiedClassName(typeNameNode);
    return this;
  }

  @Override
  public Primative setDataType(DataType dataType) {
    this.dataType = dataType;
    return this;
  }

  @Override
  public Primative setExtent(int extent) {
    this.extent = extent;
    return this;
  }

  @Override
  public void setValue(Object value) {
    this.value = value;
  }

  public void referencedInFrame() {
    this.refInFrame = true;
  }

  public boolean isReferencedInFrame() {
    return refInFrame;
  }

}
