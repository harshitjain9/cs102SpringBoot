import { SelectColumnFilter } from "./SelectColumnFilter.jsx";

// abbrVslM: "A EXPLORER"
// avg_SPEED: null
// berthN: "S03"
// bthgDt: "2021-04-10T08:45:00"
// count: 0
// displayColor: null
// distance_TO_GO: null
// firstBthgDt: null
// fullInVoyN: "AE080421"
// fullVslM: "ASEAN EXPLORER"
// inVoyN: "TEST"
// is_PATCHING_ACTIVATED: null
// max_SPEED: null
// outVoyN: "080421"
// patching_PREDICTED_BTR: null
// predicted_BTR: null
// shiftSeqN: "1"
// status: "ALONGSIDE"
// unbthgDt: "2021-04-12T12:00:00"
// vessel_NAME: null
// voyage_CODE_INBOUND: null
// vsl_VOY: null
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