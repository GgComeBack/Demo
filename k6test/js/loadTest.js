import http from 'k6/http';
import papaparse from './papaparse/papaparse.min.js';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';
import { SharedArray } from 'k6/data';

export let options = {
  stages: [
      { duration: "10s", target: 100 },
      { duration: "100s", target: 100 },
      { duration: "10s", target: 100 }
  ],
   noConnectionReuse: true
};

// not using SharedArray here will mean that the code in the function call (that is what loads and
// parses the csv) will be executed per each VU which also means that there will be a complete copy
// per each VU

const csvData = new SharedArray('another data name', function () {
  // Load CSV file and parse it using Papa Parse
  return papaparse.parse(open('./dataSiren.csv'), { header: true }).data;
});

export default function () {
  const uri = "http://concentrateur-server:8080/concentrateur";

  const params = {
    headers: {
      'Content-Type': 'application/stream+json',
    },
  };

  const url = uri + "?page=" + Math.floor(Math.random() * 3) + "&size=" + (1+Math.floor(Math.random() * 100)) + "&siret=" + csvData[Math.floor(Math.random() * csvData.length)].siren + "%25";
  console.log(url);
  const response = http.get(url, params);
    check(response, {
      'response code was 20X': (res) => res.status == 200 || res.status == 206 || res.status == 204,
    });

}