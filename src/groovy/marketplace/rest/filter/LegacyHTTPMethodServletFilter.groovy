package marketplace.rest.filter

import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletInputStream

class LegacyHTTPMethodServletFilter implements Filter {
    private static final String METHOD_PARAM_KEY = '_method'

    /**
     * A ServletRequest that gets its HTTP method from either the HTTP headers, or if present,
     * a _method form parameter
     */
    private static class LegacyHTTPMethodServletRequest extends HttpServletRequestWrapper {

        final String method
        private final byte[] payload
        final String queryString

        LegacyHTTPMethodServletRequest(HttpServletRequest request) throws Exception {
            super(request)
            this.method = getLegacyHTTPMethod(request)
            this.payload = request.getInputStream().bytes
        }

        @Override
        public ServletInputStream getInputStream () throws IOException {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload);

            ServletInputStream inputStream = new ServletInputStream() {
                public int read () 
                    throws IOException {
                    return byteArrayInputStream.read();
                }
            };
            return inputStream
        }

        @Override
        public BufferedReader getReader() throws IOException {
            InputStream sourceStream = this.getInputStream()
            Reader sourceReader = (this.characterEncoding != null) ?
                new InputStreamReader(sourceStream, this.characterEncoding) : new InputStreamReader(sourceStream)
            return new BufferedReader(sourceReader)
        }

        @Override
        public String getQueryString() {
            if (this.method == 'GET') {
                Map<String, String> parameters = this.getParameterMap();
                String urlParams = ''

                for (Map.Entry<String, String> entry : parameters.entrySet())
                {
                    if (entry.getKey() != '_method') {
                        urlParams += entry.getKey() + '=' + entry.getValue()[0] + '&'
                    }
                }
                return urlParams

            } else {
                return super.getQueryString()
            }            
        }
    }

    private static final String getLegacyHTTPMethod(request) {
        String requestMethod = request.method
        if (requestMethod == 'POST') {
            return request.getParameter(METHOD_PARAM_KEY) ?: requestMethod
        }
        else {
            return requestMethod
        }
    }

    void destroy() {}

    void init(FilterConfig config) {}

    void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) {

        filterChain.doFilter(new LegacyHTTPMethodServletRequest(request), (HttpServletResponse) response)
    }
}
