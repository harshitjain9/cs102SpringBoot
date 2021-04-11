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
export const COLUMNS = [

    {
        Header: "Vessel's Name",
        accessor: "vessel_NAME ",
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