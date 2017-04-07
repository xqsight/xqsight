/*
 * Copyright 2014 ptma@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xqsight.common.freemarker;

import lombok.Data;

@Data
public class TemplateElement {

    private String templateName;
    private String engine;
    private String templateFile;
    private String targetPath;
    private String targetFileName;
    private String encoding;
    private boolean selected;

    public TemplateElement(String templateName, String engine, String templateFile, String targetPath, String targetFileName, String encoding) {
        super();
        this.templateName = templateName;
        this.engine = engine;
        this.templateFile = templateFile;
        this.targetPath = targetPath;
        this.targetFileName = targetFileName;
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return templateName + " <" + engine + ">";
    }
}
