package com.wisdom.action;

import java.util.Map;

/**
 * 消息处理接口
 * Created by fusj on 15/12/23.
 */
public interface IAction {
    String execute(Map<String, String> paramMap) throws Exception;
}
