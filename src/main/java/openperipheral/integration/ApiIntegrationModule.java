package openperipheral.integration;

import cpw.mods.fml.common.ModAPIManager;
import openmods.integration.IIntegrationModule;

public abstract class ApiIntegrationModule implements IIntegrationModule {

	public abstract String getApiId();

	@Override
	public String name() {
		return getApiId() + " (API) CC integration module";
	}

	@Override
	public boolean canLoad() {
		final String apiId = getApiId();
		System.out.println("Checking for API: " + apiId);
		System.out.println("API found: " + ModAPIManager.INSTANCE.hasAPI(apiId));
		return ModAPIManager.INSTANCE.hasAPI(apiId);
	}
}
