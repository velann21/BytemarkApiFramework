package co.bytemark.componenttests.workflows.resetpin;

import co.bytemark.helper.ConstantVariables;
import co.bytemark.helper.ExcelUtility;
import co.bytemark.helper.Given;
import co.bytemark.helper.Then;
import co.bytemark.helper.When;
import co.bytemark.instances.ClassInstances;
import co.bytemark.models.resetpin.ResetPinRequestModel;
import co.bytemark.models.resetpin.ResetPinRootResponseModel;
import io.restassured.specification.RequestSpecification;

public class ResetPinServiceImpl extends ClassInstances implements ResetPinService {

	ResetPinRootResponseModel responseObject;
	public void given(int startRow, String sheetName) {
		RequestSpecification givenP = Given.preRequisites().givenP();
		
		
		resetPinRequestModel.setOauth_token(ExcelUtility.valueExtracter(startRow, ConstantVariables.RESETPIN_OAUTHTOKEN,
				ConstantVariables.RSESTPIN_TESTDATA_SHEET));
		resetPinRequestModel.setClient_id(ExcelUtility.valueExtracter(startRow, ConstantVariables.RESETPIN_CLIENT_ID,
				ConstantVariables.RSESTPIN_TESTDATA_SHEET));
	}

	public ResetPinRootResponseModel when() {
		 responseObject = When.whenActions().postResponse_PojoMapper(resetPinRequestModel,
				ConstantVariables.RESET_PIN_REQUEST, ConstantVariables.RESETPINREQUEST_JSONFILE,
				ResetPinRootResponseModel.class);
		return responseObject;
	}

	public void then() {

		//Then.thenValidation().assertEquals(jsonPath, value)responseObject.getData()
		
	}

	public ResetPinRootResponseModel resetPinRequest(int startRow, String sheetName) {
		resetPinService.given(startRow, sheetName);
		ResetPinRootResponseModel responseObject = resetPinService.when();
		resetPinService.then();
		return responseObject;
	}

}
