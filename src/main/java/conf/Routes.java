/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import ninja.AssetsController;
import ninja.Route;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

import controllers.ApiController;
import controllers.ApplicationController;
import controllers.BookController;
import controllers.BorrowerController;
import controllers.CORSFilter;
import controllers.LoginLogoutController;
import controllers.StudentController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) { 
    	router.OPTIONS().route("/.*").with(CORSFilter::cors);
        if (!ninjaProperties.isProd()) {
            router.GET().route("/setup").with(ApplicationController::setup);
        }
        router.GET().route("/index").with(ApplicationController::index);
        
        ////////////////////////////////////////////////////////////////////////
        //////////// Books
        ////////////////////////////////////////////////////////////////////////
		router.GET().route("/api/books").with(ApiController::getbooksJson);
		router.POST().route("/newbook").with(BookController::newBookAdd);
		router.DELETE().route("/delete/{b_id}").with(BookController::deleteBook);
		router.PUT().route("/update").with(BookController::updateBook);
		router.GET().route("/showbook").with(BookController::showAllBooks);
		router.GET().route("/getbookbyid/{b_id}").with(BookController::findBookbyId);
		router.GET().route("/getbookbytitle/{b_title}").with(BookController::findBookbyTitle);
		router.GET().route("/getbookbyauthor/{b_author}").with(BookController::findBookbyAuthor);
		
		/////////////////////////////////////////////////////////////////////////
		//Student
		/////////////////////////////////////////////////////////////////////////
		router.GET().route("/api/students").with(ApiController::getstudentsJson);
		router.GET().route("/students").with(StudentController::ShowStudents);
		router.POST().route("/newentry").with(BorrowerController::newentry);
		router.GET().route("/borrowerlist").with(BorrowerController::showAllRecords);
		
		
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController::login);
        router.POST().route("/loginpost").with(LoginLogoutController::loginPost);
        router.GET().route("/logout").with(LoginLogoutController::logout);
        
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
