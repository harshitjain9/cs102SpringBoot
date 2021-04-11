import { SelectColumnFilter } from "./SelectColumnFilter.jsx";

// abbrVslM: "ABHIMATA 1"
// avg_SPEED: null
// berthN: "K09"
// bthgDt: "2021-03-29T08:30:00"
// count: 1
// displayColor: "red"
// distance_TO_GO: null
// firstBthgDt: "2021-03-29T10:00:00"
// fullInVoyN: "B125A"
// fullVslM: "ABHIMATA 1"
// inVoyN: "B125A"
// is_PATCHING_ACTIVATED: null
// max_SPEED: null
// outVoyN: "B125A"
// patching_PREDICTED_BTR: null
// predicted_BTR: null
// shiftSeqN: null
// status: "UNBERTHED"
// unbthgDt: "2021-03-29T11:00:00"
// vessel_NAME: null
// voyage_CODE_INBOUND: null
// vsl_VOY: null
// __proto__: Object
// {AIS_ETA_SGT=2021-04-12 11:30, AIS_TIMESTAMP_SGT=2021-04-11 20:57, AVG_SPEED=13.5, CURRENT_PORT=, CURRENT_PORT_COUNTRY=, CURRENT_PORT_UNLOCODE=, DECLARED_BTR=2021-04-12 12:00, DESTINATION=SINGAPORE PEGBA, DISTANCE_TO_GO=157, DISTANCE_TRAVELLED=1339, DRAUGHT=66, END_OF_PORT_CALL_SGT=2021-04-07 06:56, ETA_CALC_SGT=2021-04-12 08:38, ETA_UPDATED_SGT=2021-04-11 20:36, IMO=9197349, IS_PATCHING_ACTIVATED=0, LAST_DEPARTED_PORT=BDCGP, LAST_PORT=CHITTAGONG, LAST_PORT_COUNTRY=BD, LAST_PORT_TIME_SGT=2021-04-07 23:05, LAST_PORT_UNLOCODE=BDCGP, MAX_SPEED=16.1, NEXT_PORT_COUNTRY=SG, NEXT_PORT_NAME=SINGAPORE, NEXT_PORT_UNLOCODE=SGSIN, PATCHING_PREDICTED_BTR=2021-04-12 06:30, PREDICTED_BTR=2021-04-12 09:28, PREDICTION_TS=2021-04-11 20:31, SPEED=116, START_OF_PORT_CALL_SGT=2021-04-04 18:53, VESSEL_NAME=BANGKOK, VOYAGE_CODE_INBOUND=102S, VOYAGE_CODE_OUTBOUND=103N, VSL_VOY=BANGKOK102S}
// No records for vsl_voy, 'BATAMINDAH1010739B'
export const COLUMNS = [

    {
        Header: "Vessel's Name",
        accessor: "fullVslM",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Incoming Voyage Number",
        accessor: "inVoyN",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Outgoing Voyage Number",
        accessor: "outVoyN",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Average Speed",
        accessor: "avg_SPEED",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Max Speed",
        accessor: "max_SPEED",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Distance To Go",
        accessor: "distance_TO_GO",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Berthing Time",
        accessor: "predicted_BTR",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Departure Time",
        accessor: "unbthgDt",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Berth Number",
        accessor: "berthN",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Status",
        accessor: "status",
        Filter: SelectColumnFilter,
        disableSortBy: true,
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    }

]