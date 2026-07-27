/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.crmclient.business;

import org.apache.commons.lang3.StringUtils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * AbstractCRMItem
 *
 */
public abstract class CRMItem implements ICRMItem
{
    private static final long serialVersionUID = 4324796594271864562L;
    private static final String PROPERTY_WS_CRM_REST_WEBAPP_URL = "crmclient.crm.rest.webapp.url";
    private static final Config _config = ConfigProvider.getConfig( );

    // Private parameters
    private Map<String, String> _mapParameters = new LinkedHashMap<String, String>( );
    private String _strmCRMWebAppCode;

    /**
     * Constructor
     */
    public CRMItem( )
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameters( Map<String, String> mapParameters )
    {
        _mapParameters = mapParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getParameters( )
    {
        return _mapParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putParameter( String strKey, String strValue )
    {
        _mapParameters.put( strKey, strValue );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCRMWebAppCode( )
    {
        return _strmCRMWebAppCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCRMWebAppCode( String _strmCRMWebAppCode )
    {
        this._strmCRMWebAppCode = _strmCRMWebAppCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCRMWebAppBaseURL( )
    {
        StringBuffer strPropertyWebAppUrl = new StringBuffer( );
        strPropertyWebAppUrl.append( PROPERTY_WS_CRM_REST_WEBAPP_URL );

        if ( !StringUtils.isBlank( getCRMWebAppCode( ) ) )
        {
            strPropertyWebAppUrl.append( "." );
            strPropertyWebAppUrl.append( getCRMWebAppCode( ) );
        }

        return _config.getOptionalValue( strPropertyWebAppUrl.toString( ), String.class ).orElse( null );
    }
}
