export const COLUMNS = [

    {
        Header: "Vessel's Long Name",
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
        Header: "Time",
        accessor: "time",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    },
    {
        Header: "Message",
        accessor: "message",
        Cell: ({ value }) => {
            if (value == null) {
                return "N/A";
            }
            return value
        }
    }

]