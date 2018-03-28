package co.bytemark.componenttests.workflows.resetpin;

import co.bytemark.models.resetpin.ResetPinRootResponseModel;

public interface ResetPinService {
	public void given(int startRow, String sheetName);

	public ResetPinRootResponseModel when();

	public void then();
	
	public ResetPinRootResponseModel resetPinRequest(int startRow, String sheetName);
}
