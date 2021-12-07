package visitors.api;

import cmdline.api.params.BaseParams;

/**
 * Common definition for directors of visitor builders.
 */
public interface VisitorBuilderDirector<T extends BaseParams> {

    VisitorBuilderDirector<T> buildVisitor(T params);

    Visitor getVisitor();
}
