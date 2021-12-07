package cmdline.impl.params;

import cmdline.api.params.*;

public final class ParamsFactory {

    private static final ParamsFactory INSTANCE = new ParamsFactory();

    private ParamsFactory(){}

    public static ParamsFactory instance() {return INSTANCE;}

    public BaseParams createBaseParams() {return new BaseParamsImpl();}

    public CommonParams createCommonParams() {return new CommonParamsImpl();}

    public FileParams createFileParams() {return new FileParamsImpl();}

    public LangParams createLangParams() {return new LangParamsImpl();}

    public UnitParams createUnitParams() {return new UnitParamsImpl();}

    public ProgLangParams createProgLangParams() {
        return new ProgLangsParamsImpl();
    }

    public SizeParams createSizeParams() {
        return new SizeParamsImpl();
    }

}
