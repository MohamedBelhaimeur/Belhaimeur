package cmdline.impl.contenttypes;

import cmdline.api.params.CommonParams;
import visitors.api.VisitorBuilderDirector;
import com.google.inject.Inject;
import visitors.api.CTypesVisitorBuilder;
import visitors.api.Visitor;

/**
 * A director for the builder which creates the visitor for the ctype command line.
 */
final class CTypesVisitorBuilderDirector implements VisitorBuilderDirector<CommonParams> {
    @Inject
    @CTypesVisitorBuilder.FileContentType
    private CTypesVisitorBuilder builder;

    @Override
    public CTypesVisitorBuilderDirector buildVisitor(CommonParams params) {
        builder.setSizeUnit(params.getSizeUnit());
        return this;
    }

    @Override
    public Visitor getVisitor() {
        return builder.build();
    }
}
