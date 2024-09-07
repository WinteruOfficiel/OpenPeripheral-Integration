package openperipheral.integration.EnderIO;

import openperipheral.integration.ModIntegrationModule;
import openperipheral.integration.OpcAccess;

public class ModuleEnderIO extends ModIntegrationModule {

	@Override
	public String getModId() {
		return "EnderIO";
	}

	@Override
	public void load() {
		OpcAccess.adapterRegistry.register(new powerConduitAdaptor());
	}
}
