package com.wisdom.core.interfaces;

import com.wisdom.core.model.IMappDatapackage;

import java.util.Map;

public interface IClientHandler<T extends IMappDatapackage> extends IHandler
{
	public T doHandlePackage(T requestObject, Map<String, Object> attributes) throws Exception;
}
