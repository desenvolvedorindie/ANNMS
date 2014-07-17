# Copyright (c) 2013, Welsiton Ferreira (wfcreations@gmail.com)
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification,
# are permitted provided that the following conditions are met:
#
#   Redistributions of source code must retain the above copyright notice, this
#   list of conditions and the following disclaimer.
#
#   Redistributions in binary form must reproduce the above copyright notice, this
#   list of conditions and the following disclaimer in the documentation and/or
#   other materials provided with the distribution.
#
#   Neither the name of the WFCreations nor the names of its
#   contributors may be used to endorse or promote products derived from
#   this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
# ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
# ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
# ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

#
# IDL for Artificial Neural Network Management System Service
#

namespace java br.com.wfcreations.annms.api.thrift
namespace cpp br.com.wfcreations.annms.api
namespace csharp WFCreations.ANNMS.API
namespace py annms
namespace php annms
namespace perl ANNMS

#
# Constants
#

const string VERSION = "1.0.0"

#
# Types
#

typedef i32 int

#
# Structures
#

/**
 *	SQLANNResults
 *
 *	@param string data Data.
 */
struct SQLANNResults {
	1: required string data
}

#
# Exceptions
#

/**
 *	AuthenticationException
 *
 *	@param int code Error code.
 *	@param string message Error message.
 */
exception AuthenticationException {
    1: required int code
	2: required string message
}

/**
 *	AuthorizationException
 *
 *	@param int code Error code.
 *	@param string message Error message.
 */
exception AuthorizationException {
    1: required int code
	2: required string message
}

/**
 *	AuthorizationException
 *
 *	@param int code Error code.
 *	@param string message Error message.
 */
exception TimedOutException {
    1: required int code
	2: required string message
}

#
# Services
#

/**
 *	ANNSService
 */
service ANNMSService {
	/**
	 *	Connect and autenticate in server
	 *
	 *	@param string username
	 *	@param string password
	 *	@throws AuthenticationException
	 */
	void connect(1:required string username, 2:required string password) throws (1: AuthenticationException ae),
	
	/**
	 *	Execute query in server
	 *
	 *	@param string query
	 *	@return SQLANNResults
	 *	@throws AuthorizationException
	 */
	SQLANNResults execute (1: required string query) throws (1: AuthorizationException ae, 2: TimedOutException to)
}