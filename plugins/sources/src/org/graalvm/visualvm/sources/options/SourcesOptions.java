/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.visualvm.sources.options;

import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.graalvm.visualvm.core.options.UISupport;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Jiri Sedlacek
 */
@OptionsPanelController.TopLevelRegistration(
        id = "SourcesOptions",                                                  // NOI18N
        categoryName = "#SourcesOptions_Name",                                  // NOI18N
        iconBase = "org/graalvm/visualvm/sources/resources/sources32.png",      // NOI18N
        position = 1530)
@NbBundle.Messages({
        "SourcesOptions_Name=Sources"                                           // NOI18N
})
public final class SourcesOptions extends OptionsPanelController {
    
    private static final HelpCtx HELP_CTX = null;
    
    
    private SourcesOptionsPanel optionsPanel;
    private JComponent optionsComponent;
    

    @Override
    public void update() {
        if (optionsPanel == null) return;
        optionsPanel.load(NbPreferences.forModule(SourcesOptions.class));
    }

    @Override
    public void applyChanges() {
        if (optionsPanel == null) return;
        optionsPanel.save(NbPreferences.forModule(SourcesOptions.class));
    }

    @Override
    public void cancel() {
        if (optionsPanel == null) return;
        optionsPanel.cancel();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isChanged() {
        if (optionsPanel == null) return false;
        return optionsPanel.dirty(NbPreferences.forModule(SourcesOptions.class));
    }

    @Override
    public JComponent getComponent(Lookup lkp) {
        if (optionsComponent == null) optionsComponent = UISupport.createScrollableContainer(getPanel());
        return optionsComponent;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HELP_CTX;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
    }
    
    
    private SourcesOptionsPanel getPanel() {
        if (optionsPanel == null) optionsPanel = new SourcesOptionsPanel();
        return optionsPanel;
    }
    
}