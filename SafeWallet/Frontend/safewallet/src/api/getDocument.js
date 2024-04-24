import axios from "axios";
import { URL_DOWNLOAD_DOCUMENT } from "../URLS/URL";

async function getDocument(data) {
  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
    responseType: 'blob' 
  };

  // eslint-disable-next-line no-useless-catch
  try {
    const response = await axios.post(URL_DOWNLOAD_DOCUMENT, data, config);
    return response;
  } catch (error) {
    throw error;
  }
}

export default getDocument;
