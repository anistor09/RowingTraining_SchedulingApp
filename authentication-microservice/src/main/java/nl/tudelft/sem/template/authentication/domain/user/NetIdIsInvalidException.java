package nl.tudelft.sem.template.authentication.domain.user;

/**
 * Exception to indicate the NetID is already in use.
 */
public class NetIdIsInvalidException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public NetIdIsInvalidException(NetId netId) {
        super(netId.toString());
    }
}
