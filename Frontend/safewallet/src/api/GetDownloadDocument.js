import axios from "axios";
import URL_DOWNLOAD_DOCUMENT from "../URLS/URL";

function getDownloadDocument() {
  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  };

  return axios.get(URL_DOWNLOAD_DOCUMENT, config)
    .then(response => response.data)
    .catch(error => {
      throw error;
    });
}

export default getDownloadDocument;
