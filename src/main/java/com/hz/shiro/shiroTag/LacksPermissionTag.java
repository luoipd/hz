package com.hz.shiro.shiroTag;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.LacksPermissionTag}
 * </p>
 */
public class LacksPermissionTag extends PermissionTag {
	protected boolean showTagBody(String p) {
		return !isPermitted(p);
	}
}
