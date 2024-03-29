/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
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
package com.infobite.com.draggableview.transformer;

import android.view.View;

/**
 * Factory created to provide Transformer implementations like ResizeTransformer o
 * ScaleTransformer.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class TransformerFactory {

  public Transformer getTransformer(final boolean resize, final View view, final View parent) {
    Transformer transformer = null;
    if (resize) {
      transformer = new ResizeTransformer(view, parent);
    } else {
      transformer = new ScaleTransformer(view, parent);
    }
    return transformer;
  }
}
