package cmdline.impl.proglangs;

import cmdline.api.params.ProgLangParams;
import visitors.api.VisitorBuilderDirector;
import com.google.inject.Inject;
import visitors.api.ProgLangsVisitorBuilder;
import visitors.api.Visitor;

final class ProgLangsVisitorBuilderDirector implements VisitorBuilderDirector<ProgLangParams> {

    @Inject
    @ProgLangsVisitorBuilder.ProgLanguages
    private ProgLangsVisitorBuilder builder;

    @Override
    public ProgLangsVisitorBuilderDirector buildVisitor(ProgLangParams params) {
        builder.setMediaTypes(params.getMediaTypes())
                .setSizeUnit(params.getSizeUnit());
        return this;
    }

    @Override
    public Visitor getVisitor() {
        return builder.build();
    }
}
