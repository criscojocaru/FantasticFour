import React, { useContext, useEffect, useState } from 'react';
import Layout from '../layout/layout';
import styles from '../../styling/pages/home.module.scss';
import CustomButton from '../custom_components/button';
import { Link } from 'react-router-dom';
import Context from '../../context/context';

const Home = () => {
  const [offset, setOffset] = useState(0);
  const { state } = useContext(Context);

  const onScroll = () => {
    setOffset(window.pageYOffset);
  };

  useEffect(() => {
    window.addEventListener('scroll', onScroll);
  }, []);

  return (
    <Layout>
      <div className={styles.container}>
        <div className={styles.group}>
          <div className={styles.welcome}>
            Bine ați venit,
            <br /> masa dumneavoastră
            <br /> vă așteaptă!
          </div>
          {offset > window.innerHeight / 2 && (
            <Link to={state.token === null ? '/login' : '/tables'}>
              <CustomButton>Rezervă acum o masă </CustomButton>
            </Link>
          )}
        </div>
        <div className={styles.burger}>
          <svg
            xmlns='http://www.w3.org/2000/svg'
            width='518.655'
            height={`${470.053}`}
            viewBox={`0 0 518.655 ${
              470.053 + (offset / window.innerHeight) * 100
            }`}
          >
            <g id='burger' transform='translate(-334.467 26.384)'>
              <g id='burger_1'>
                <path
                  id='Path_2'
                  data-name='Path 2'
                  d='M407.718,618.4S370.273,692.5,402.141,692.5s433.413,1.593,444.567-19.121c7.815-14.513,3.506-66.178.226-95.813a22.659,22.659,0,0,0-23.776-20.133c-86.843,4.793-411.273,22.557-409.863,20.34,2.789-4.382.8-3.187.8-3.187S401.344,615.217,407.718,618.4Z'
                  transform={`translate(-38.166 ${
                    -248.858 + (offset / window.innerHeight) * 100
                  })`}
                  fill='#bfaa79'
                />
                <path
                  id='Path_3'
                  data-name='Path 3'
                  d='M847.73,577.565a22.659,22.659,0,0,0-23.776-20.133c-86.843,4.793-411.273,22.557-409.863,20.34,2.789-4.382.8-3.187.8-3.187s-2.845,9.071-5.175,18.977c.33-.033.577.278-.5,2.171q-.144.641-.283,1.282c12.994,1.284,324.6-15.779,409.446-20.462a22.659,22.659,0,0,1,23.776,20.133c2.439,22.033,5.445,56.24,3.615,78.306a4.764,4.764,0,0,0,1.736-1.615C855.319,658.865,851.011,607.2,847.73,577.565Z'
                  transform={`translate(-38.166 ${
                    -248.858 + (offset / window.innerHeight) * 100
                  })`}
                  opacity='0.1'
                />
              </g>
              <path
                id='Path_4'
                data-name='Path 4'
                d='M452.35,507.362c-4.151-1.272-9.4-2.915-8.931-5.594.252-1.423,2.156-2.534,3.536-3.727,2.66-2.3,3.377-5.54.52-7.762s-9.739-2.519-12.26-.168c-1.3,1.209-1.219,2.726-1.888,4.092-2.089,4.261-10.539,6.132-17.718,7.788s-15.211,4.736-14.536,9.144c.736,4.809,11.811,9.075,7.355,13.176-1.616,1.487-5.015,2.317-5.738,4.018-.509,1.2.513,2.414,1.506,3.507l7.052,7.77c2.806,3.091,5.7,6.455,4.757,9.9-1.863,6.82-17,9.43-29,8.663-4.395-.281-9.56-.665-12.579,1.182-2.327,1.423-2.323,3.64-1.55,5.537,1.991,4.883,8.36,9.112,16.511,10.963-9.136,1.8-12.724,9.4-6.416,13.586a18.772,18.772,0,0,1-11.187.321c-3.12,2.872-1.286,6.813,2.671,9.326s9.562,3.926,14.99,5.268l-5.079,4.749c3.676,2.871,11.614,1.859,16.967.044s11.161-4.251,17.159-3.3c5.625.89,9.879,4.642,15.606,4,4.438-.5,6.551-3.35,7.933-5.812l5.878-10.466c8.368,1.309,17.166-4.424,13.805-9,9.364,1.006,18.58-5.075,15.506-10.231,4.987,3.846,8.5,8.314,10.215,13,.768,2.094,1.235,4.307,3.325,6.081a11.045,11.045,0,0,0,9.662,1.748c-1.708,3.311-3.091,7.173.418,9.983s13.634,2.013,13.187-1.43c5.351,2.3,11.347,4.46,18.034,4.632s14.006-2.373,14.614-6.184a93.428,93.428,0,0,0,29.206,4.7c.844-2.55,6.739-3.375,11.147-2.752s8.5,2.121,13.036,2.123,9.423-2.73,7.1-4.96a93.847,93.847,0,0,0,29.088,1.468c9.163-.973,17.846-3.285,27.125-3.783a64.787,64.787,0,0,1,27.613,4.361c3.042,1.275,6.822,2.881,10.164,1.88,1.9-.567,2.817-1.793,4.1-2.77,5.542-4.2,16.875-3.4,25.618-1.745s18.914,3.643,26.575.721c2.891-1.1,5.021-2.806,8.1-3.731,11.015-3.315,24.2,5.18,35.89,2.736,3.807-.8,6.9-2.7,10.929-2.935,7.153-.417,11.88,4.432,18.836,5.474,7.217,1.082,15.04-3.309,13.279-7.454a64.784,64.784,0,0,0,20.483-2.847l-3.276-5.9c8.164-.131,14.169-5.984,10.387-10.122,8.187-1.559,14.66-5.755,16.342-10.593s-1.491-10.131-8.011-13.362c-3.848-1.907-8.9-3.248-11.1-5.873-1.584-1.891-1.353-4.091-1.969-6.158-1.349-4.524-7.194-8.518-14.745-10.076-3.279-.676-6.868-.933-9.837-1.978s-5.114-3.307-3.4-5.043c.907-.919,2.654-1.46,3.783-2.294,4.58-3.382-3.273-7.663-9.977-9.562l16.01-.524,3.438-15.587c-6.334,5.152-22.765,4.068-26.881-1.773,5.143,1.688,12.041-.47,14.555-3.54s1.9-6.641,1.225-10.009a4.857,4.857,0,0,0-2.668-3.975c-1.567-.746-4.064-1.058-4.578-2.186-.524-1.151,1.5-2.169,1.8-3.347.577-2.26-4.617-3.256-8.125-4.349-8.277-2.579-12.284-9.825-21.7-9.59-9.344.234-11.955,7.368-11.962,12.715s-3.168,12.5-12.52,12.4c-3.734-.039-6.99-1.382-9.971-2.667L745,467.713a64.133,64.133,0,0,0-11-3.943c-5.4-1.261-11.37-1.41-17.2-1.542l-37.466-.851a17.453,17.453,0,0,0-5.683.489c-1.968.658-2.924,1.952-4.11,3.065-5.217,4.9-17.482,6.817-26.334,4.119-2.347-.715-4.45-1.7-6.937-2.24-7.834-1.7-16.054,1.443-24.193,2.578-13.342,1.86-29.923-1.342-39.542,4.262-3.207,1.868-4.908,4.423-7.88,6.414s-8.334,3.341-12.082,1.838c.471,3.654.9,7.467-1.574,10.847s-8.818,6.139-15.052,5.286c-3.308-.874-3.047-3.655-1.483-5.537s3.964-3.8,3.184-5.84c-.774-2.019-4.315-3.193-6.963-4.6s-4.414-4.075-1.6-5.374c-1.9-2.436-8.707-2.139-11.7-.1s-3.3,4.919-3.622,7.579-1.039,5.572-4.511,7.352c-5.917,3.033-17.276,1.459-21.2,5.41-1.247,1.255-1.32,2.769-2.033,4.153-3.183,6.179-16.926,7.779-28.16,8.284a13.784,13.784,0,0,0-.156,3.9C461.538,511.6,455.135,508.216,452.35,507.362Z'
                // transform='translate(-35.737 -251.718)'
                transform={`translate(-35.737 ${
                  -251.718 + (offset / window.innerHeight) * 90
                })`}
                fill='#61ab43'
              />
              <path
                id='Path_5'
                data-name='Path 5'
                d='M841.5,500.3c.671,3.368,1.289,6.939-1.225,10.009a7.681,7.681,0,0,1-1.259,1.191l10.147-.332,3.438-15.587c-2.624,2.134-6.981,3.2-11.478,3.294a10.006,10.006,0,0,1,.377,1.424Z'
                // transform='translate(-172.501 -330.225)'
                transform={`translate(-172.501 ${
                  -330.225 + (offset / window.innerHeight) * 90
                })`}
                opacity='0.1'
              />
              <path
                id='Path_6'
                data-name='Path 6'
                d='M844.455,518.664c-.4-2.107-3.874-4.245-7.763-5.774a15.805,15.805,0,0,1-10.973.963C828.558,517.882,837.254,519.646,844.455,518.664Z'
                // transform='translate(-172.501 -336.598)'
                transform={`translate(-172.501 ${
                  -336.598 + (offset / window.innerHeight) * 50
                })`}
                opacity='0.1'
              />
              <path
                id='Path_7'
                data-name='Path 7'
                d='M849.426,530.021l-.263,1.192-16.01.524c6.7,1.9,14.557,6.179,9.977,9.562-1.13.834-2.876,1.375-3.783,2.294-1.714,1.736.431,4,3.4,5.043s6.558,1.3,9.837,1.978c7.551,1.557,13.4,5.552,14.745,10.076.616,2.067.385,4.267,1.97,6.158,2.2,2.626,7.254,3.967,11.1,5.873.371.184.727.376,1.076.573,3.479-1.968,5.98-4.505,6.935-7.251,1.683-4.838-1.491-10.131-8.011-13.362-3.849-1.907-8.9-3.248-11.1-5.873-1.584-1.891-1.353-4.091-1.97-6.158-1.349-4.524-7.194-8.518-14.745-10.076C851.553,530.359,850.49,530.189,849.426,530.021Z'
                // transform='translate(-172.501 -353.499)'
                transform={`translate(-172.501 ${
                  -353.499 + (offset / window.innerHeight) * 50
                })`}
                opacity='0.1'
              />
              <path
                id='Path_8'
                data-name='Path 8'
                d='M380.378,601.8c1.246-2.653,4.43-5.01,9.005-5.912a30.464,30.464,0,0,1-12.064-5.442,18.225,18.225,0,0,1-5.539-.693c-3.12,2.872-1.286,6.813,2.671,9.326a28.732,28.732,0,0,0,5.927,2.721Z'
                // transform='translate(80.521 -251.027)'
                transform={`translate(80.521 ${
                  -251.027 + (offset / window.innerHeight) * 50
                })`}
                opacity='0.1'
              />
              <path
                id='Path_9'
                data-name='Path 9'
                d='M408.618,524.115a53.261,53.261,0,0,1,6.991-2.083c7.179-1.655,15.629-3.526,17.718-7.788.67-1.366.591-2.883,1.888-4.092,2.521-2.351,9.4-2.054,12.26.168s2.14,5.462-.52,7.762c-1.38,1.193-3.284,2.3-3.536,3.727-.473,2.679,4.78,4.321,8.931,5.594a17.652,17.652,0,0,1,5.405,2.851c.035-.284.069-.568.1-.849,11.233-.5,24.976-2.1,28.16-8.284.713-1.384.787-2.9,2.033-4.153,3.924-3.951,15.283-2.377,21.2-5.41,3.472-1.78,4.184-4.693,4.511-7.352s.626-5.536,3.623-7.579,9.808-2.34,11.7.1c-2.812,1.3-1.046,3.967,1.6,5.374s6.189,2.582,6.963,4.6c.78,2.035-1.62,3.958-3.184,5.84s-1.825,4.662,1.483,5.537c6.233.852,12.578-1.906,15.052-5.286s2.045-7.193,1.574-10.847c3.748,1.5,9.11.153,12.082-1.838s4.673-4.546,7.88-6.414c9.619-5.6,26.2-2.4,39.541-4.262,8.139-1.135,16.359-4.276,24.193-2.578,2.487.539,4.59,1.525,6.937,2.24,8.852,2.7,21.118.779,26.334-4.119,1.185-1.113,2.142-2.408,4.11-3.065a17.45,17.45,0,0,1,5.683-.489l37.466.851c5.83.132,11.8.281,17.2,1.542a64.138,64.138,0,0,1,11,3.943L771.778,499.3c2.981,1.286,6.237,2.629,9.971,2.667,9.351.1,12.513-7.053,12.52-12.4s2.618-12.481,11.962-12.715c9.417-.236,13.425,7.011,21.7,9.59,3.508,1.093,8.7,2.089,8.125,4.349-.268,1.049-1.9,1.971-1.885,2.974a12.328,12.328,0,0,0,6.1-3.5c2.515-3.07,1.9-6.641,1.225-10.009a4.856,4.856,0,0,0-2.668-3.975c-1.567-.746-4.064-1.058-4.578-2.186-.524-1.151,1.5-2.169,1.8-3.347.577-2.26-4.617-3.256-8.125-4.349-8.277-2.579-12.284-9.825-21.7-9.59-9.344.234-11.955,7.368-11.962,12.715s-3.168,12.5-12.52,12.4c-3.734-.039-6.99-1.382-9.971-2.667L745,467.713a64.141,64.141,0,0,0-11-3.943c-5.4-1.261-11.37-1.41-17.2-1.542l-37.466-.851a17.452,17.452,0,0,0-5.683.489c-1.968.658-2.924,1.952-4.11,3.065-5.217,4.9-17.482,6.817-26.334,4.119-2.347-.715-4.45-1.7-6.937-2.24-7.834-1.7-16.054,1.443-24.193,2.578-13.342,1.86-29.923-1.342-39.541,4.262-3.207,1.868-4.908,4.423-7.88,6.414s-8.334,3.341-12.082,1.838c.471,3.654.9,7.467-1.574,10.847s-8.818,6.139-15.052,5.286c-3.308-.874-3.047-3.655-1.483-5.537s3.964-3.8,3.184-5.84c-.774-2.019-4.315-3.193-6.963-4.6s-4.414-4.075-1.6-5.374c-1.9-2.436-8.707-2.139-11.7-.1s-3.3,4.919-3.623,7.579-1.039,5.572-4.511,7.352c-5.917,3.033-17.276,1.459-21.2,5.41-1.247,1.255-1.32,2.769-2.033,4.153-3.183,6.179-16.926,7.779-28.16,8.284-.027.281-.062.565-.1.849a17.652,17.652,0,0,0-5.405-2.851c-4.151-1.272-9.4-2.915-8.931-5.594.252-1.423,2.156-2.534,3.536-3.727,2.66-2.3,3.377-5.54.52-7.762s-9.739-2.519-12.26-.168c-1.3,1.209-1.219,2.726-1.888,4.092-2.089,4.261-10.539,6.132-17.718,7.788s-15.211,4.736-14.536,9.144C401.8,515.868,412.522,520.075,408.618,524.115Z'
                // transform='translate(-38.166 -253.355)'
                transform={`translate(-38.166 ${
                  -253.355 + (offset / window.innerHeight) * 50
                })`}
                opacity='0.1'
              />
              <path
                id='Path_10'
                data-name='Path 10'
                d='M389.383,575.847a15.738,15.738,0,0,0-5.557,2.175c1.075.047,2.146.118,3.175.183,12,.766,27.14-1.843,29-8.663.94-3.441-1.951-6.8-4.757-9.9l-3.383-3.728c-5.871,2-13.894,2.691-20.863,2.246-4.394-.281-9.56-.665-12.579,1.182-2.327,1.423-2.323,3.64-1.55,5.537C374.863,569.767,381.232,574,389.383,575.847Z'
                // transform='translate(78.578 -251.653)'
                transform={`translate(78.578 ${
                  -251.653 + (offset / window.innerHeight) * 50
                })`}
                opacity='0.1'
              />
              <ellipse
                id='Ellipse_1'
                data-name='Ellipse 1'
                cx='119.507'
                cy='78.875'
                rx='119.507'
                ry='78.875'
                transform={`translate(524.837 ${
                  132.81 + (offset / window.innerHeight) * 30
                })`}
                // transform='translate(524.837 132.81)'
                fill='#ea565c'
              />
              <path
                id='Path_11'
                data-name='Path 11'
                d='M593.156,460.209s-54.177,5.577-38.242-17.528,35.852-31.072,45.413-24.7,13.544,23.9,11.951,23.9-24.7-13.544-32.665-3.187S593.156,460.209,593.156,460.209Z'
                // transform='translate(31.956 -254.838)'
                transform={`translate(31.956 ${
                  -254.838 + (offset / window.innerHeight) * 40
                })`}
                fill='#fff'
                opacity='0.2'
              />
              <path
                id='Path_12'
                data-name='Path 12'
                d='M640.1,462.467s44.738,31.06,17.6,38.21-47.443.221-50.544-10.843,5.293-26.957,6.5-25.919,9.916,26.365,22.708,23.7S640.1,462.467,640.1,462.467Z'
                // transform='translate(18.232 -253.69)'
                transform={`translate(18.232 ${
                  -253.69 + (offset / window.innerHeight) * 40
                })`}
                fill='#fff'
                opacity='0.2'
              />
              <path
                id='Path_13'
                data-name='Path 13'
                d='M650.543,443s3.531-54.348,23.656-34.786,24.662,40.529,16.784,48.894-25.825,9.371-25.559,7.8,17.471-22.1,8.587-31.677S650.543,443,650.543,443Z'
                // transform='translate(9.829 -254.961)'
                transform={`translate(9.829 ${
                  -254.961 + (offset / window.innerHeight) * 30
                })`}
                fill='#fff'
                opacity='0.2'
              />
              <ellipse
                id='Ellipse_2'
                data-name='Ellipse 2'
                cx='119.507'
                cy='78.875'
                rx='119.507'
                ry='78.875'
                // transform='translate(432.46 126.21)'
                transform={`translate(432.46 ${
                  126.21 + (offset / window.innerHeight) * 30
                })`}
                fill='#ea565c'
              />
              <path
                id='Path_14'
                data-name='Path 14'
                d='M448.95,453.835s-54.177,5.577-38.242-17.528,35.852-31.072,45.413-24.7,13.544,23.9,11.951,23.9-24.7-13.544-32.665-3.187S448.95,453.835,448.95,453.835Z'
                // transform='translate(68.562 -255.003)'
                fill='#fff'
                opacity='0.2'
                transform={`translate(68.562 ${
                  -255.003 + (offset / window.innerHeight) * 30
                })`}
              />
              <path
                id='Path_15'
                data-name='Path 15'
                d='M495.893,456.093s44.738,31.06,17.6,38.21-47.443.221-50.544-10.843,5.293-26.957,6.5-25.919,9.916,26.365,22.708,23.7S495.893,456.093,495.893,456.093Z'
                // transform='translate(54.798 -253.853)'
                transform={`translate(54.798 ${
                  -253.853 + (offset / window.innerHeight) * 30
                })`}
                fill='#fff'
                opacity='0.2'
              />
              <path
                id='Path_16'
                data-name='Path 16'
                d='M506.337,436.628s3.531-54.348,23.656-34.786,24.662,40.529,16.784,48.894-25.825,9.371-25.559,7.8,17.471-22.1,8.587-31.677S506.337,436.628,506.337,436.628Z'
                // transform='translate(45.464 -255.133)'
                transform={`translate(45.464 ${
                  -255.133 + (offset / window.innerHeight) * 30
                })`}
                fill='#fff'
                opacity='0.2'
              />
              <path
                id='Path_17'
                data-name='Path 17'
                d='M551.775,392.286c26.031,14.469,42.576,36.134,42.576,60.354,0,43.561-53.505,78.875-119.507,78.875-22.616,0-43.763-4.147-61.794-11.351,20.789,11.555,47.625,18.521,76.932,18.521,66,0,119.507-35.313,119.507-78.875C609.488,431.175,586.367,406.106,551.775,392.286Z'
                // transform='translate(53.175 -254.116)'
                transform={`translate(53.175 ${
                  -254.116 + (offset / window.innerHeight) * 30
                })`}
                opacity='0.1'
              />
              <path
                id='Path_18'
                data-name='Path 18'
                d='M695.981,397.863c26.031,14.469,42.576,36.134,42.576,60.354,0,43.561-53.505,78.875-119.507,78.875-22.616,0-43.763-4.147-61.794-11.351,20.789,11.555,47.625,18.521,76.932,18.521,66,0,119.507-35.313,119.507-78.875C753.694,436.752,730.572,411.683,695.981,397.863Z'
                // transform='translate(6.026 -253.925)'
                transform={`translate(6.026 ${
                  -253.925 + (offset / window.innerHeight) * 30
                })`}
                opacity='0.1'
              />
              <path
                id='Path_19'
                data-name='Path 19'
                d='M416.955,444.3s102.776-109.947,354.538-64.534c199.02,35.9,35.591,97.687-41.618,122A277.4,277.4,0,0,1,660.817,514.2C573.714,518.687,354.625,522.7,416.955,444.3Z'
                // transform='translate(-38.166 -254.904)'
                transform={`translate(-38.166 ${
                  -254.904 + (offset / window.innerHeight) * 30
                })`}
                fill='#714b4f'
              />
              <path
                id='Path_20'
                data-name='Path 20'
                d='M729.875,477.07A277.393,277.393,0,0,1,660.817,489.5c-70.562,3.634-227.736,6.953-251.75-33.6-30.322,65.54,169.523,61.732,251.75,57.5a277.4,277.4,0,0,0,69.058-12.434c56.593-17.821,159.5-55.776,132.052-88.912C843.446,438.064,773.132,463.448,729.875,477.07Z'
                // transform='translate(-38.166 -254.179)'
                transform={`translate(-38.166 ${
                  -254.179 + (offset / window.innerHeight) * 30
                })`}
                opacity='0.1'
              />
              <line
                id='Line_1'
                data-name='Line 1'
                x2='27.885'
                y2='54.973'
                // transform='translate(652.362 124.501)'
                transform={`translate(652.362 ${
                  124.501 + (offset / window.innerHeight) * 30
                })`}
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <line
                id='Line_2'
                data-name='Line 2'
                x2='23.901'
                y2='46.209'
                // transform='translate(587.532 144.852)'
                transform={`translate(587.532 ${
                  144.852 + (offset / window.innerHeight) * 30
                })`}
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <line
                id='Line_3'
                data-name='Line 3'
                x2='37.446'
                y2='54.973'
                transform='translate(688.764 124.501)'
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <line
                id='Line_4'
                data-name='Line 4'
                y1='31.869'
                x2='250.965'
                transform='translate(512.239 164.306)'
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <line
                id='Line_5'
                data-name='Line 5'
                x2='17.528'
                y2='24.698'
                transform='translate(753.643 134.842)'
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <line
                id='Line_6'
                data-name='Line 6'
                x2='11.154'
                y2='23.105'
                transform='translate(551.26 164.213)'
                fill='none'
                stroke='#000'
                strokeMiterlimit='10'
                strokeWidth='2'
                opacity='0.1'
              />
              <path
                id='Path_21'
                data-name='Path 21'
                d='M538.471,344.356s229.182-1.037,231.285-3.111,50.462-3.172,59.924,10.307,12.616,19.7,0,21.774S401.8,587.951,401.8,587.951s-23.128,2.074-11.564-20.737S538.471,344.356,538.471,344.356Z'
                // transform='translate(-38.166 -254.147)'
                transform={`translate(-38.166 ${
                  -254.147 + (offset / window.innerHeight) * 30
                })`}
                fill='#f6c964'
              />
              <path
                id='Path_22'
                data-name='Path 22'
                d='M634.688,439.161c0,8.58-41.377,29.478-92.419,29.478s-92.419-20.9-92.419-29.478,41.377-11.951,92.419-11.951S634.688,430.581,634.688,439.161Z'
                // transform='translate(42.94 -254.588)'
                transform={`translate(42.94 ${
                  -254.588 + (offset / window.innerHeight) * 20
                })`}
                fill='#61ab43'
              />
              <ellipse
                id='Ellipse_3'
                data-name='Ellipse 3'
                cx='92.419'
                cy='15.536'
                rx='92.419'
                ry='15.536'
                transform='translate(492.79 168.42)'
                fill='#61ab43'
              />
              <path
                id='Path_23'
                data-name='Path 23'
                d='M746.228,424.82c0,8.58-41.377,29.478-92.419,29.478S561.39,433.4,561.39,424.82s41.377-11.951,92.419-11.951S746.228,416.24,746.228,424.82Z'
                // transform='translate(7.348 -254.955)'
                transform={`translate(7.348 ${
                  -254.955 + (offset / window.innerHeight) * 20
                })`}
                fill='#61ab43'
              />
              <ellipse
                id='Ellipse_4'
                data-name='Ellipse 4'
                cx='92.419'
                cy='15.536'
                rx='92.419'
                ry='15.536'
                transform='translate(568.738 153.721)'
                fill='#61ab43'
              />
              <path
                id='Path_24'
                data-name='Path 24'
                d='M857.768,410.48c0,8.58-41.377,29.478-92.419,29.478s-92.419-20.9-92.419-29.478,41.377-11.951,92.419-11.951S857.768,401.9,857.768,410.48Z'
                transform='translate(-38.166 -255.322)'
                fill='#61ab43'
              />
              <ellipse
                id='Ellipse_5'
                data-name='Ellipse 5'
                cx='92.419'
                cy='15.536'
                rx='92.419'
                ry='15.536'
                transform='translate(634.764 139.022)'
                fill='#61ab43'
              />
              <path
                id='Path_25'
                data-name='Path 25'
                d='M424.988,414.893s-38.01-6.188-18.563-37.126,25.988-65.62,34.474-73.369c9.488-8.663,205.313-160.543,357.87,7.521a224.218,224.218,0,0,1,38.9,60.75c1.845,4.2,3.88,7.97,5.869,9.959,6.188,6.188,11.491,23.867,0,32.707S493.495,458.649,424.988,414.893Z'
                transform='translate(-38.166 -259.554)'
                fill='#dbc288'
              />
              <path
                id='Path_26'
                data-name='Path 26'
                d='M603.106,286.277a14.87,14.87,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652A14.869,14.869,0,0,1,603.106,286.277Z'
                transform='translate(29.395 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_27'
                data-name='Path 27'
                d='M539.461,269.482a14.87,14.87,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652A14.869,14.869,0,0,1,539.461,269.482Z'
                transform='translate(44.443 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_28'
                data-name='Path 28'
                d='M712.354,293.956a14.869,14.869,0,0,1-8.385.156c-4.193-1.312-7.238-3.508-6.8-4.906s4.191-1.467,8.385-.156a14.87,14.87,0,0,1,6.8,4.906Z'
                transform='translate(3.522 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_29'
                data-name='Path 29'
                d='M594.787,248.874a14.87,14.87,0,0,1-8.385.156c-4.193-1.312-7.238-3.508-6.8-4.906s4.191-1.467,8.385-.156a14.87,14.87,0,0,1,6.8,4.906Z'
                transform='translate(31.289 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_30'
                data-name='Path 30'
                d='M722.961,264.786a14.869,14.869,0,0,1-8.385.156c-4.193-1.312-7.238-3.508-6.8-4.906s4.191-1.467,8.385-.156a14.869,14.869,0,0,1,6.8,4.906Z'
                transform='translate(1.016 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_31'
                data-name='Path 31'
                d='M696.805,262.41a14.869,14.869,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652A14.87,14.87,0,0,1,696.805,262.41Z'
                transform='translate(7.241 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_32'
                data-name='Path 32'
                d='M620.785,246.5a14.869,14.869,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652a14.869,14.869,0,0,1,7.956,2.652Z'
                transform='translate(25.215 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_33'
                data-name='Path 33'
                d='M679.126,246.5a14.869,14.869,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652a14.869,14.869,0,0,1,7.956,2.652Z'
                transform='translate(11.421 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_34'
                data-name='Path 34'
                d='M657.027,280.973a14.869,14.869,0,0,1-7.956,2.652c-4.394,0-7.956-1.187-7.956-2.652s3.562-2.652,7.956-2.652a14.869,14.869,0,0,1,7.956,2.652Z'
                transform='translate(16.646 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_35'
                data-name='Path 35'
                d='M592.75,274.778a14.87,14.87,0,0,1,4.171-7.275c3.279-2.925,6.727-4.411,7.7-3.318s-.892,4.35-4.171,7.275A14.869,14.869,0,0,1,592.75,274.778Z'
                transform='translate(28.588 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_36'
                data-name='Path 36'
                d='M552.971,265.055a14.869,14.869,0,0,1,4.171-7.275c3.279-2.925,6.727-4.411,7.7-3.318s-.892,4.35-4.171,7.275a14.87,14.87,0,0,1-7.7,3.318Z'
                transform='translate(37.939 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_37'
                data-name='Path 37'
                d='M484.023,312.789a14.869,14.869,0,0,1,4.171-7.275c3.279-2.925,6.727-4.411,7.7-3.318s-.892,4.35-4.171,7.275a14.869,14.869,0,0,1-7.7,3.318Z'
                transform='translate(54.147 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_38'
                data-name='Path 38'
                d='M636.948,259.751a14.87,14.87,0,0,1,4.171-7.275c3.279-2.925,6.727-4.411,7.7-3.318s-.892,4.35-4.171,7.275A14.869,14.869,0,0,1,636.948,259.751Z'
                transform='translate(18.199 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_39'
                data-name='Path 39'
                d='M621.036,291.574a14.868,14.868,0,0,1,4.171-7.275c3.279-2.925,6.727-4.411,7.7-3.318s-.892,4.35-4.171,7.275a14.87,14.87,0,0,1-7.7,3.318Z'
                transform='translate(21.939 -259.554)'
                fill='#f2f2f2'
              />
              <path
                id='Path_55'
                data-name='Path 55'
                d='M259.7,683.368c-13.241.107-30.766-2.062-34.294-4.211-2.687-1.636-3.757-7.509-4.115-10.218-.248.011-.392.015-.392.015s.743,9.459,4.271,11.608,21.053,4.318,34.294,4.211c3.822-.031,5.142-1.391,5.07-3.4C264,682.585,262.541,683.345,259.7,683.368Z'
                transform='translate(116 -248.858)'
                opacity='0.2'
              />
            </g>
          </svg>
        </div>
      </div>
    </Layout>
  );
};

export default Home;
