package visitors.impl.proglangs;

import cmdline.impl.common.SizeUnit;
import org.apache.tika.mime.MediaType;
import visitors.api.ProgLangsVisitorBuilder;
import visitors.api.Visitor;
import visitors.api.VisitorBuilder;

import java.util.List;

final class ProgLangsVisitorBuilderImpl implements ProgLangsVisitorBuilder {

    private final ProgLangsVisitor visitor;

    public ProgLangsVisitorBuilderImpl(){
        this.visitor = new ProgLangsVisitor();
    }

    @Override
    public VisitorBuilder setSizeUnit(SizeUnit sizeUnit) {
        visitor.setSizeUnit(sizeUnit);
        return this;
    }

    @Override
    public Visitor build() {
        return visitor;
    }

    @Override
    public ProgLangsVisitorBuilder setMediaTypes(List<MediaType> mediaTypes) {
        visitor.setMediaTypes(mediaTypes);
        return this;
    }
}
