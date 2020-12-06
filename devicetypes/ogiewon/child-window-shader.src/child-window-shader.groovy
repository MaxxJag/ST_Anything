/**
 *  Child Window Shade
 *
 *  Copyright 2020 Jean-Guy Rivard
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Change History:
 *
 *    Date        Who            What
 *    ----        ---            ----
 *    2020-12-05  Jean-Guy Rivard  Original Creation
 *
 *  
 */
metadata {
	definition (name: "Child Window Shade", namespace: "ogiewon", author: "Jean-Guy Rivard", ofcDeviceType: "oic.d.blind", vid: "generic-shade-3") {
		capability "Window Shade"
		capability "Sensor"

//		attribute "lastUpdated", "String"
	}

	simulator {

	}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "lighting", width: 6, height: 4){
			tileAttribute ("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label:'${name}', action:"close", icon:"http://i.imgur.com/4TbsR54.png", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening"
				attributeState "partially open", label:'Open', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "opening", label:'${name}', action:"stop", icon:"st.shades.shade-opening", backgroundColor:"#79b821", nextState:"partially open"
				attributeState "closing", label:'${name}', action:"stop", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"partially open"
			}
		}
	}
}

// handle commands
def open() {
	sendData("on")
}

def close() {
	sendData("off")
}

def presetPosition() {
	sendData("preset")
}

def parse(String description) {
    log.debug "parse(${description}) called"
	def parts = description.split(" ")
    def name  = parts.length>0?parts[0].trim():null
    def value = parts.length>1?parts[1].trim():null
	if (name && value) {
        sendEvent(name: name, value: value)
    }
    else {
    	log.debug "Missing either name or value.  Cannot parse!"
    }
}


def installed() {
}
