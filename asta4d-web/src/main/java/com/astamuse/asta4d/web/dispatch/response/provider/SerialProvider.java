/*
 * Copyright 2012 astamuse company,Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.astamuse.asta4d.web.dispatch.response.provider;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.astamuse.asta4d.web.dispatch.mapping.UrlMappingRule;

@SuppressWarnings("rawtypes")
public class SerialProvider implements ContentProvider {

    private List<ContentProvider> contentProviderList = new ArrayList<>();

    public SerialProvider() {
    }

    public SerialProvider(ContentProvider... contentProviders) {
        for (ContentProvider contentProvider : contentProviders) {
            contentProviderList.add(contentProvider);
        }
    }

    public SerialProvider(List<ContentProvider> contentProviders) {
        contentProviderList.addAll(contentProviders);
    }

    public List<ContentProvider> getContentProviderList() {
        return contentProviderList;
    }

    @Override
    public boolean isContinuable() {
        for (ContentProvider cp : contentProviderList) {
            if (!cp.isContinuable()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void produce(UrlMappingRule currentRule, HttpServletResponse response) throws Exception {
        for (ContentProvider cp : contentProviderList) {
            cp.produce(currentRule, response);
        }
    }

}