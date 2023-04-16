package com.alpha.rules;

import com.alpha.entity.Request;
import com.alpha.entity.Response;

/**
 * @author harish.kumar-mbp
 * @created 15/04/23
 */
public interface IRule {

    public Response executeRule(Request request);
}
