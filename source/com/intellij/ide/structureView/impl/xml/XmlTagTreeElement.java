/*
 * Copyright (c) 2004 JetBrains s.r.o. All  Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * -Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduct the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the distribution.
 *
 * Neither the name of JetBrains or IntelliJ IDEA
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. JETBRAINS AND ITS LICENSORS SHALL NOT
 * BE LIABLE FOR ANY DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT
 * OF OR RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL JETBRAINS OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN
 * IF JETBRAINS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 */
package com.intellij.ide.structureView.impl.xml;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

public class XmlTagTreeElement extends PsiTreeElementBase<XmlTag>{
  public XmlTagTreeElement(XmlTag tag) {
    super(tag);
  }

  public StructureViewTreeElement[] getChildrenBase() {
    XmlTag[] subTags = getElement().getSubTags();
    StructureViewTreeElement[] result = new StructureViewTreeElement[subTags.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = new XmlTagTreeElement(subTags[i]);
    }
    return result;
  }

  public String getPresentableText() {
    final StringBuffer buffer = new StringBuffer();
    buffer.append(getElement().getName());
    final XmlAttribute[] attributes = getElement().getAttributes();
    for (int i = 0; i < attributes.length; i++) {
      appendAttribute(attributes[i], buffer);
    }
    return buffer.toString();
  }

  private void appendAttribute(final XmlAttribute attribute, final StringBuffer buffer) {
    buffer.append(" ");
    buffer.append(attribute.getName());
    buffer.append("=");
    buffer.append("\"");
    buffer.append(attribute.getValue());
    buffer.append("\"");
  }
}
