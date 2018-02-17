/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.designpatterns.behavioral.mediator.ch1;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-05-24 03:05:01 PM
 * @version 1.0.0
 * @since 1.0.0
 */
class TestMediator {            

    public static void main(String[] args) 
    {
        Mediator mediator = new Mediator();
        Title title = new LowerCaseTitle("Mr. Rohtash Singh", mediator);
      
        System.out.println("title.getTitle() :" +  title.getTitle());
        System.out.println("mediator.getTitle().getTitle() :" +  mediator.getTitle().getTitle());
//        System.out.println("LowerCase super title :" + lowerCaseTitle.getTitle());   
//        System.out.println("UpperCase UC title :" + upperCaseTitle.getUpcaseTitle());
//        System.out.println("UpperCase super title :" + upperCaseTitle.getTitle());   
        
        title = new UpperCaseTitle(title.getTitle(), mediator);
        System.out.println("title.getTitle() :" +  title.getTitle());
        System.out.println("mediator.getTitle().getTitle() :" +  mediator.getTitle().getTitle());
//        lowerCaseTitle.setSuperTitleLowercase();
//        
//        System.out.println(" ");         
//        System.out.println("After Super set to LC");    
//        System.out.println("Lowercase LC title :" + lowerCaseTitle.getLowercaseTitle());
//        System.out.println("Lowercase super title :" + lowerCaseTitle.getTitle());   
//        System.out.println("Upcase UC title :" + upperCaseTitle.getUpcaseTitle());
//        System.out.println("Upcase super title :" + upperCaseTitle.getTitle());  
    }
 } 