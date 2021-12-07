package visitors.impl.contenttypes;

import cmdline.impl.common.SizeUnit;
import visitors.api.CTypesVisitorBuilder;
import visitors.api.Visitor;
import visitors.api.VisitorBuilder;

public class CTypesVisitorBuilderImpl implements CTypesVisitorBuilder {

    private final CTypesVisitor visitor;

    CTypesVisitorBuilderImpl() {
        visitor = new CTypesVisitor();
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
}
