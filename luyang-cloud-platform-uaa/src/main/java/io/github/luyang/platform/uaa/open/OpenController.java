package io.github.luyang.platform.uaa.open;

import io.github.luyang.platform.uaa.open.beans.body.AuthorizeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/open")
public class OpenController {

	private final OpenService openService;

	@GetMapping("/authorize")
	public ResponseEntity<?> authorize(
		@RequestParam("response_type") String responseType,
		@RequestParam("client_id") String clientId,
		@RequestParam(value = "redirect_uri", required = false) String redirectUri,
		@RequestParam(value = "scope", required = false) String scope,
		@RequestParam(value = "state", required = false) String state,
		@RequestParam(value = "code_challenge", required = false) String codeChallenge,
		@RequestParam(value = "code_challenge_method", required = false) String codeChallengeMethod) {

		AuthorizeRequest authorizeRequest = new AuthorizeRequest(
			responseType,clientId,redirectUri,scope,state,codeChallenge,codeChallengeMethod
		);

		return openService.authorize(authorizeRequest);
	}
}
